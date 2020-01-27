package com.example.retailstore.database;

import android.content.Context;

import com.example.retailstore.database.dao.ProductDao;
import com.example.retailstore.database.data.DataGenerator;
import com.example.retailstore.database.data.Product;

import java.util.List;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class ProductsRoomDatabase extends RoomDatabase
{

    public static final String DATABASE_NAME = "products_db";
    private static volatile ProductsRoomDatabase INSTANCE;

    public static ProductsRoomDatabase getInstance(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (ProductsRoomDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductsRoomDatabase.class, DATABASE_NAME)
                            .build();
                    INSTANCE.populateInitialData();
                }
            }
        }
        return INSTANCE;
    }

    public abstract ProductDao productDao();

    private void populateInitialData()
    {
        Executors.newSingleThreadExecutor().execute(() -> {
            if (productDao().count() == 0)
            {
                runInTransaction(() ->
                {
                    List<Product> products = DataGenerator.generateProducts();
                    productDao().insertAll(products);
                });
            }
        });
    }
}

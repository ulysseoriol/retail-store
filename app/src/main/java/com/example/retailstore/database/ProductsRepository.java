package com.example.retailstore.database;

import android.app.Application;
import android.os.AsyncTask;

import com.example.retailstore.database.data.Product;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.room.Room;

public class ProductsRepository
{
    private MediatorLiveData<List<Product>> mObservableProducts;
    private static ProductsRepository INSTANCE;
    private final ProductsRoomDatabase database;

    ProductsRepository(Application application)
    {
        database = ProductsRoomDatabase.getInstance(application);

        mObservableProducts = new MediatorLiveData<>();
        mObservableProducts.addSource(database.productDao().getProducts(),
                products -> {
                        mObservableProducts.setValue(products);
                });

    }

    public static ProductsRepository getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ProductsRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ProductsRepository(application);
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<List<Product>> getAllProducts()
    {
        return mObservableProducts;
    }

    public void updateIsInCart(boolean isInCart, String productName)
    {
        if(database != null)
        {

            Executors.newSingleThreadExecutor().execute(() -> {
                database.productDao().updateIsInCart(isInCart, productName);
            });
        }
    }

    public Product getProduct(String productName)
    {
        mObservableProducts.getValue().get()
    }
}

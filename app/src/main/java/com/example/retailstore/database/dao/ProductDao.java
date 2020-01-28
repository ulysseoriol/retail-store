package com.example.retailstore.database.dao;

import com.example.retailstore.database.data.Product;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ProductDao
{
    @Query("SELECT * FROM " + Product.TABLE_NAME)
    LiveData<List<Product>> getProducts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Product> products);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduct(Product product);

    @Query("DELETE FROM " + Product.TABLE_NAME)
    void deleteAll();

    @Query("SELECT COUNT(*) FROM " + Product.TABLE_NAME)
    int getCount();

    @Query("SELECT * FROM " + Product.TABLE_NAME + " WHERE isInCart = 1")
    LiveData<List<Product>> getCartProducts();

    @Query("UPDATE PRODUCTS_TABLE SET isInCart = :isInCart WHERE productName = :productName")
    void updateIsInCart(boolean isInCart, String productName);

    @Query("SELECT * FROM " + Product.TABLE_NAME + " WHERE productName = :productName")
    LiveData<Product> getProduct(String productName);

    @Query("DELETE FROM " + Product.TABLE_NAME)
    void clearTable();
}

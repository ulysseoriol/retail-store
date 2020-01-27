package com.example.retailstore.ui.cart;

import android.app.Application;

import com.example.retailstore.database.ProductsRepository;
import com.example.retailstore.database.data.Product;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CartViewModel extends AndroidViewModel
{

    private ProductsRepository mRepository;
    private LiveData<List<Product>> mProducts;

    public CartViewModel(@NonNull Application application)
    {
        super(application);
        mRepository = ProductsRepository.getInstance(application);
        mProducts = mRepository.getAllProducts();
    }

    public LiveData<List<Product>> getAllProducts()
    {
        return mProducts;
    }

    public void removeFromCart(int position)
    {
        mProducts.getValue().get(position).setInCart(false);
    }
}
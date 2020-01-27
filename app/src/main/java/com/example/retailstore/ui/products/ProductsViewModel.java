package com.example.retailstore.ui.products;

import android.app.Application;

import com.example.retailstore.database.ProductsRepository;
import com.example.retailstore.database.data.Product;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ProductsViewModel extends AndroidViewModel
{

    private ProductsRepository mRepository;
    private LiveData<List<Product>> mProducts;

    public ProductsViewModel(@NonNull Application application)
    {
        super(application);
        mRepository = ProductsRepository.getInstance(application);
        mProducts = mRepository.getAllProducts();
    }

    public LiveData<List<Product>> getAllProducts()
    {
        return mProducts;
    }


    public void addToCart(int position)
    {
        mRepository.updateIsInCart(true, mProducts.getValue().get(position).getName());
    }

    public void removeFromCart(int position)
    {
        mRepository.updateIsInCart(false, mProducts.getValue().get(position).getName());
    }

    public LiveData<Product> getProduct(String productName)
    {
        return mRepository.getProduct(productName);
    }

    public void addProduct(Product product)
    {
        mRepository.addProduct(product);
    }
}
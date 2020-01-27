package com.example.retailstore.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retailstore.R;
import com.example.retailstore.database.data.Product;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductsFragment extends Fragment
{

    private ProductsViewModel productsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        productsViewModel =
                ViewModelProviders.of(this).get(ProductsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_products, container, false);

        final RecyclerView recyclerView = root.findViewById(R.id.product_list);
        final ProductsAdapter adapter = new ProductsAdapter(productsViewModel, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        productsViewModel.getAllProducts().observe(this, new Observer<List<Product>>()
        {
            @Override
            public void onChanged(@Nullable List<Product> products)
            {
                if (products != null)
                {
                    adapter.setProducts(products);
                }
            }
        });
        return root;
    }
}
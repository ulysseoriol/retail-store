package com.example.retailstore.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.retailstore.R;
import com.example.retailstore.ui.products.ProductsViewModel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartFragment extends Fragment
{

    private ProductsViewModel viewModel;

    private TextView priceTotalTV;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        viewModel =
                ViewModelProviders.of(this).get(ProductsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        priceTotalTV = root.findViewById(R.id.priceTotal);
        final RecyclerView recyclerView = root.findViewById(R.id.cart_product_list);
        final CartAdapter adapter = new CartAdapter(viewModel, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        viewModel.getAllProducts().observe(this, products ->  adapter.setProducts(products));

        setHasOptionsMenu(false);

        return root;
    }
}
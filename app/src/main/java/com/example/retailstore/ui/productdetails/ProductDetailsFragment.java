package com.example.retailstore.ui.productdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.retailstore.R;
import com.example.retailstore.database.data.Product;
import com.example.retailstore.ui.products.ProductsViewModel;

public class ProductDetailsFragment extends Fragment
{

    private ProductsViewModel viewModel;

    public final static String EXTRA_PRODUCT_NAME = "com.example.retailstore.ui.productdetails.ProductDetailsFragment.EXTRA_PRODUCT_NAME";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        viewModel =
                ViewModelProviders.of(this).get(ProductsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_product_details, container, false);
        final TextView textView = root.findViewById(R.id.text_send);

        String productName= "";
        if (getArguments() != null)
        {
            productName = getArguments().getString(EXTRA_PRODUCT_NAME);
        }
        else
        {
            getActivity().getSupportFragmentManager().popBackStack();
        }

        viewModel.getProduct(productName).observe(this, new Observer<Product>()
        {
            @Override
            public void onChanged(@Nullable Product product)
            {
                textView.setText(product.getName());
            }
        });
        return root;
    }
}
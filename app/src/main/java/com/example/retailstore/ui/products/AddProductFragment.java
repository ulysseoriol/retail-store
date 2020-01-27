package com.example.retailstore.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.retailstore.R;
import com.example.retailstore.database.data.Product;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class AddProductFragment extends Fragment
{

    private ProductsViewModel productsViewModel;
    private EditText productNameEditText;
    private EditText productPriceEditText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        productsViewModel =
                ViewModelProviders.of(this).get(ProductsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_product, container, false);

        productNameEditText = root.findViewById(R.id.product_name_edit);
        productPriceEditText = root.findViewById(R.id.product_price_edit);
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu)
    {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.findItem(R.id.action_filter);
        if(item != null)
        {
            item.setVisible(false);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
    {
        inflater.inflate(R.menu.add_product_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {

        if (productNameEditText.getText().length() > 0)
        {
            Product product = new Product();
            product.setName(productNameEditText.getText().toString());
            product.setPrice(productPriceEditText.getText().length() > 0 ?
                    Double.valueOf(productPriceEditText.getText().toString()) : 0);
            product.setInCart(false);
            productsViewModel.addProduct(product);
            getActivity().getSupportFragmentManager().popBackStack();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
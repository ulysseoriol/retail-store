package com.example.retailstore.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retailstore.R;
import com.example.retailstore.database.data.Product;
import com.example.retailstore.ui.products.ProductsViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.GenericViewHolder>
{
    private List<Product> products;
    private ProductsViewModel viewModel;
    private Context context;

    public CartAdapter(ProductsViewModel viewModel, Context context)
    {
        this.viewModel = viewModel;
        this.context = context;
    }


    @Override
    public int getItemCount()
    {
        return products == null ? 0 : products.size();
    }

    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
        return new GenericViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position)
    {
        holder.bind(viewModel, position);
    }

    public void setProducts(List<Product> products)
    {
        List<Product> productList = new ArrayList<>();
        for (Product product :
                products)
        {
            if (product.isInCart())
            {
                productList.add(product);
            }
        }
        this.products = productList;
        notifyDataSetChanged();
    }

    class GenericViewHolder extends RecyclerView.ViewHolder
    {

        TextView productNameTV;
        TextView productPriceTV;
        TextView actionButton;


        public GenericViewHolder(View view)
        {
            super(view);
            productNameTV = itemView.findViewById(R.id.productName);
            productPriceTV = itemView.findViewById(R.id.productPrice);
            actionButton = itemView.findViewById(R.id.actionButton);
        }

        void bind(final ProductsViewModel viewModel, final Integer position)
        {
            productNameTV.setText(products.get(position).getName());
            productPriceTV.setText(String.valueOf(products.get(position).getPrice()));
            actionButton.setText(R.string.delete_button_title);
            actionButton.setOnClickListener(view ->
            {
                viewModel.removeFromCart(products.get(position).getName());
                Toast.makeText(context
                        ,products.get(position).getName()  + " " + context.getResources().getString(R.string.product_removed_text)
                        , Toast.LENGTH_LONG).show();
                notifyDataSetChanged();
            });
        }

    }
}

package com.example.retailstore.ui.products;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retailstore.R;
import com.example.retailstore.database.data.Product;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.GenericViewHolder>
{
        private List<Product> products;
        private ProductsViewModel viewModel;
        private Context context;

        public ProductsAdapter(ProductsViewModel viewModel, Context context) {
            this.viewModel = viewModel;
            this.context = context;
        }


        @Override
        public int getItemCount() {
            return products == null ? 0 : products.size();
        }

        public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
            return new GenericViewHolder(view);
        }

        @Override
        public void onBindViewHolder(GenericViewHolder holder, int position)
        {
            holder.bind(viewModel, position);
        }

        public void setProducts(List<Product> products) {
            this.products = products;
            notifyDataSetChanged();
        }

        class GenericViewHolder extends RecyclerView.ViewHolder {

            TextView productName;
            TextView productPrice;
            TextView actionButton;
            CardView cardView;

            public GenericViewHolder(View view)
            {
                super(view);
                productName = itemView.findViewById(R.id.productName);
                productPrice = itemView.findViewById(R.id.productPrice);
                actionButton = itemView.findViewById(R.id.actionButton);
                cardView = itemView.findViewById(R.id.cardView);
            }

            void bind(ProductsViewModel viewModel, Integer position)
            {
                productName.setText(products.get(position).getName());
                productPrice.setText(String.valueOf(products.get(position).getPrice()));
                actionButton.setText(R.string.add_button_title);
                actionButton.setOnClickListener(view ->
                {
                    viewModel.addToCart(position);
                    Toast.makeText(context
                            , products.get(position).getName() + " " + context.getResources().getString(R.string.product_added_text)
                            , Toast.LENGTH_LONG).show();

                });
                cardView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Bundle bundle = new Bundle();
//                        String salonJsonString =
//                                HelperMethods.getGsonParser().toJson(salonModel);bundle.putString("SALON_DETAILS", salonJsonString);
                        Navigation.findNavController(view).navigate(R.id.nav_product_details,bundle);
                    }
                });
            }

        }
}

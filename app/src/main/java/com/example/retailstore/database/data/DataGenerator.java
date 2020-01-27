package com.example.retailstore.database.data;

import com.example.retailstore.database.data.Product;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator
{

    private static final String[] PRODUCTS = new String[]{
            "Microwave oven", "Television", "Vacuum Cleaner", "Table", "Chair", "Almirah"};
    private static final String[] CATEGORIES = new String[]{
            "Electronics", "Electronics", "Electronics", "Furniture", "Furniture", "Furniture"};


    public static List<Product> generateProducts()
    {
        List<Product> products = new ArrayList<>(PRODUCTS.length);
        for (int i = 0; i < PRODUCTS.length; i++)
        {
            Product product = new Product();
            product.setName(PRODUCTS[i]);
            product.setCategory(CATEGORIES[i]);
            product.setPrice(10 + i * 10);
            product.setInCart(false);
            products.add(product);

        }
        return products;
    }
}

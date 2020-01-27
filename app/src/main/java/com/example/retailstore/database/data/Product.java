package com.example.retailstore.database.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Product.TABLE_NAME)
public class Product
{
    public static final String TABLE_NAME = "products_table";

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "productName")
    private String name;
    private double price;
    private String category;

    @ColumnInfo(name = "isInCart")
    private boolean isInCart;

    public boolean isInCart()
    {
        return isInCart;
    }

    public void setInCart(boolean inCart)
    {
        isInCart = inCart;
    }



    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }
}

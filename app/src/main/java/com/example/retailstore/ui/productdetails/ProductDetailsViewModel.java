package com.example.retailstore.ui.productdetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductDetailsViewModel extends ViewModel
{

    private MutableLiveData<String> mText;

    public ProductDetailsViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}
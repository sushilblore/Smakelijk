package com.android.sushil.smakelijk.data;

import android.support.annotation.NonNull;

public class FoodItem {
    private final String mName;
    private final String mImgUrl;
    private final String mPrice;


    public FoodItem(@NonNull String name, @NonNull String imgUrl, @NonNull String price) {
        mName = name;
        mImgUrl = imgUrl;
        mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public String getImageUrl() {
        return mImgUrl;
    }

    public String getPrice() {
        return mPrice;
    }
}

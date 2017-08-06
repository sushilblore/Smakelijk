package com.android.sushil.smakelijk.foodmenu.Presenters;

import com.android.sushil.smakelijk.foodmenu.Contracts.FoodDetailsContract;

/**
 * Created by sushiljha on 06/08/2017.
 */

public class FoodDetailsPresenter implements FoodDetailsContract.Presenter {

    public FoodDetailsPresenter(FoodDetailsContract.View view) {

    }
    @Override
    public void start() {
        loadFoodDetails();
    }

    @Override
    public void loadFoodDetails() {

    }

    @Override
    public void retryInternetAsked() {

    }
}

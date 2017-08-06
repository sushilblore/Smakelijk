package com.android.sushil.smakelijk.foodmenu.Presenters;

import android.support.annotation.NonNull;

import com.android.sushil.smakelijk.foodmenu.Contracts.FoodListContract;
import com.android.sushil.smakelijk.foodmenu.Contracts.HomeScreenContract;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by sushiljha on 06/08/2017.
 */

public class HomeScreenPresenter implements HomeScreenContract.Presenter {
    private final HomeScreenContract.View mView;

    public HomeScreenPresenter(@NonNull HomeScreenContract.View view) {
        mView = checkNotNull(view, "MainView cannot be null!");
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadFoodItems();
    }

    @Override
    public void loadFoodItems() {

    }

    @Override
    public void retryInternetAsked() {

    }
}

package com.android.sushil.smakelijk.foodmenu.Presenters;

import android.util.Log;

import com.android.sushil.smakelijk.foodmenu.Contracts.FoodListContract;
import com.android.sushil.smakelijk.models.pojo.FoodAndDrinks;
import com.android.sushil.smakelijk.networking.NetworkError;
import com.android.sushil.smakelijk.networking.Service;

import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by sushiljha on 06/08/2017.
 */

public class FoodListPresenter implements FoodListContract.Presenter {
    private final static String TAG = "FoodListPresenter";
    private final Service mService;
    private final FoodListContract.View mView;
    private CompositeSubscription mSubscriptions;

    public FoodListPresenter(Service service, FoodListContract.View view) {
        this.mService = service;
        this.mView = view;
        this.mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void start() {
        loadFoodItems();
    }

    @Override
    public void loadFoodItems() {
        mView.showWait();
        Log.d(TAG, "FoodListPresenter: loadFoodItems");
        Subscription subscription = mService.getFoodList(new Service.GetFoodListCallback() {

            @Override
            public void onSuccess(List<FoodAndDrinks> foodListResponse) {
                mView.removeWait();
                mView.getFoodListSuccess(foodListResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                mView.removeWait();
                mView.onFailure(networkError.getAppErrorMessage());
            }

        });

        mSubscriptions.add(subscription);
    }

    @Override
    public void retryInternetAsked() {

    }

    public void onStop() {
        mSubscriptions.unsubscribe();
    }
}

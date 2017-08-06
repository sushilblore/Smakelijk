package com.android.sushil.smakelijk.foodmenu.Contracts;

import com.android.sushil.smakelijk.BasePresenter;
import com.android.sushil.smakelijk.BaseView;
import com.android.sushil.smakelijk.models.pojo.Product;

/**
 * Created by sushiljha on 06/08/2017.
 */

public class FoodDetailsContract {
    public interface View extends BaseView<FoodListContract.Presenter> {
        void showWait();
        void removeWait();
        void onFailure(String appErrorMessage);
        void getFoodDetailsSuccess(Product productResponse);
    }

    public interface Presenter extends BasePresenter {
        void loadFoodDetails();
        void retryInternetAsked();
    }
}

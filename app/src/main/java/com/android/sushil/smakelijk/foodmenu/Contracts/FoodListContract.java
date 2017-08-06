package com.android.sushil.smakelijk.foodmenu.Contracts;

import com.android.sushil.smakelijk.BasePresenter;
import com.android.sushil.smakelijk.BaseView;
import com.android.sushil.smakelijk.models.pojo.FoodAndDrinks;

import java.util.List;

/**
 * Created by sushiljha on 06/08/2017.
 */

public class FoodListContract {
    public interface View extends BaseView<Presenter> {
        void showWait();
        void removeWait();
        void onFailure(String appErrorMessage);
        void getFoodListSuccess(List<FoodAndDrinks> foodListResponse);
    }

    public interface Presenter extends BasePresenter {
        void loadFoodItems();
        void retryInternetAsked();
    }
}

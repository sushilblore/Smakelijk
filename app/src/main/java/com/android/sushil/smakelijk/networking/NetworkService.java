package com.android.sushil.smakelijk.networking;

/**
 * Created by sushiljha on 06/08/2017.
 */


import com.android.sushil.smakelijk.models.pojo.FoodAndDrinks;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface NetworkService {

    @GET(".")
    Observable<List<FoodAndDrinks>> getFoodItemsList();

}

package com.android.sushil.smakelijk.networking;

/**
 * Created by sushiljha on 06/08/2017.
 */

import com.android.sushil.smakelijk.models.pojo.FoodAndDrinks;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }
    public Subscription getFoodList(final GetFoodListCallback callback) {

        return networkService.getFoodItemsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<FoodAndDrinks>>>() {
                    @Override
                    public Observable<? extends List<FoodAndDrinks>> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<List<FoodAndDrinks>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(List<FoodAndDrinks> foodListResponse) {
                        callback.onSuccess(foodListResponse);

                    }
                });
    }
    public interface GetFoodListCallback{
        void onSuccess(List<FoodAndDrinks> foodListResponse);
        void onError(NetworkError networkError);
    }
}

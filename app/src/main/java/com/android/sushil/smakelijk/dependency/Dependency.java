package com.android.sushil.smakelijk.dependency;

/**
 * Created by sushiljha on 06/08/2017.
 */

import com.android.sushil.smakelijk.foodmenu.Activity.MainActivity;
import com.android.sushil.smakelijk.foodmenu.Fragments.FoodListFragment;
import com.android.sushil.smakelijk.foodmenu.Fragments.HomeScreenFragment;
import com.android.sushil.smakelijk.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Dependency {
    void inject(MainActivity mainActivity);
    void inject(HomeScreenFragment homeScreenActivity);
    void inject(FoodListFragment foodListActivity);
}

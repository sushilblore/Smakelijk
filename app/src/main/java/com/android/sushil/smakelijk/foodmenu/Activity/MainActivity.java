package com.android.sushil.smakelijk.foodmenu.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.android.sushil.smakelijk.BaseApp;
import com.android.sushil.smakelijk.R;
import com.android.sushil.smakelijk.foodmenu.Fragments.FoodListFragment;
import com.android.sushil.smakelijk.foodmenu.Fragments.HomeScreenFragment;
import com.android.sushil.smakelijk.util.ActivityUtils;

public class MainActivity extends BaseApp implements NavigationView.OnNavigationItemSelectedListener {
    private HomeScreenFragment mHomeScreenFragment;
    public String currentFragmentTag;
    private DrawerLayout mDrawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDependency().inject(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null) {
            mHomeScreenFragment = (HomeScreenFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
            if (mHomeScreenFragment == null) {
                mHomeScreenFragment = HomeScreenFragment.newInstance();
            }
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mHomeScreenFragment, R.id.contentFrame, "HomeScreen");
        }
        else {

        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("currentFragment", currentFragmentTag);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        FoodListFragment foodListFragment = null;
        switch(item.getItemId()) {
            case R.id.nav_food:
                if (foodListFragment == null) {
                    foodListFragment = foodListFragment.newInstance(0);

                    ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(),
                            foodListFragment, R.id.contentFrame, "FoodList");
                }
                mDrawer.closeDrawers();
                break;

            case R.id.nav_drink:
                if (foodListFragment == null) {
                    foodListFragment = foodListFragment.newInstance(1);

                    ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(),
                            foodListFragment, R.id.contentFrame, "FoodList");
                }
                mDrawer.closeDrawers();
                break;
        }

        return true;
    }
}

package com.android.sushil.smakelijk;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.sushil.smakelijk.dependency.Dependency;
import com.android.sushil.smakelijk.dependency.DaggerDependency;
import com.android.sushil.smakelijk.networking.NetworkModule;

import java.io.File;

public class BaseApp  extends AppCompatActivity{
    Dependency mDependency;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        mDependency = DaggerDependency.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public Dependency getDependency() {
        return mDependency;
    }
}

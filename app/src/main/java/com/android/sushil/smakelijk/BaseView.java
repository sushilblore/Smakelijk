package com.android.sushil.smakelijk;

/**
 * Created by sushiljha on 06/08/2017.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
    boolean isActive();
}

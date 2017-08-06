package com.android.sushil.smakelijk.foodmenu.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.sushil.smakelijk.R;
import com.android.sushil.smakelijk.foodmenu.Activity.MainActivity;
import com.android.sushil.smakelijk.foodmenu.Adapters.GridAdapter;
import com.android.sushil.smakelijk.foodmenu.Contracts.HomeScreenContract;
import com.android.sushil.smakelijk.foodmenu.Presenters.HomeScreenPresenter;
import com.android.sushil.smakelijk.models.pojo.FoodAndDrinks;
import com.android.sushil.smakelijk.util.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


public class HomeScreenFragment extends Fragment implements HomeScreenContract.View {

    private HomeScreenContract.Presenter mPresenter;
    EmptyRecyclerView.Adapter mAdapter;
    EmptyRecyclerView mRecyclerView;
    EmptyRecyclerView.LayoutManager mLayoutManager;
    ProgressBar mLoadingIndicator;
    ArrayList<String> mName;
    ArrayList<Integer> mImage;
    MainActivity mParent;


    public HomeScreenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment FoodListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeScreenFragment newInstance() {
        HomeScreenFragment fragment = new HomeScreenFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (MainActivity) getActivity();
        mParent.currentFragmentTag = "HomeScreen";
        mName = new ArrayList<>(Arrays.asList("FOOD", "DRINKS"));
        mImage = new ArrayList<>(Arrays.asList(R.drawable.food_image, R.drawable.drinks_image));

        new HomeScreenPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Sushil", "HomeScreenFragment onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        // Calling the RecyclerView
        mRecyclerView = (EmptyRecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new GridAdapter(mParent, mName, mImage);
        mRecyclerView.setAdapter(mAdapter);

        // The number of Columns
        mLayoutManager = new GridLayoutManager(mParent, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mPresenter.start();
        return view;
    }


    @Override
    public void setPresenter(@NonNull HomeScreenContract.Presenter presenter) {
        checkNotNull(presenter);
        mPresenter = presenter;
    }


    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getFoodListSuccess(List<FoodAndDrinks> foodListResponse) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

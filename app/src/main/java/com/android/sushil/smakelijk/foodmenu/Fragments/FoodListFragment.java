package com.android.sushil.smakelijk.foodmenu.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.sushil.smakelijk.R;
import com.android.sushil.smakelijk.foodmenu.Activity.MainActivity;
import com.android.sushil.smakelijk.foodmenu.Adapters.FoodAdapter;
import com.android.sushil.smakelijk.foodmenu.Contracts.FoodListContract;
import com.android.sushil.smakelijk.foodmenu.Presenters.FoodListPresenter;
import com.android.sushil.smakelijk.models.pojo.FoodAndDrinks;
import com.android.sushil.smakelijk.models.pojo.Product;
import com.android.sushil.smakelijk.networking.Service;
import com.android.sushil.smakelijk.util.EmptyRecyclerView;
import java.util.List;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class FoodListFragment extends Fragment implements FoodListContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mGridPosition;

    private OnFragmentInteractionListener mListener;
    private FoodListContract.Presenter mPresenter;
    private FoodAdapter mAdapter;
    EmptyRecyclerView.LayoutManager mLayoutManager;
    View emptyView;
    EmptyRecyclerView mRecyclerView;
    ProgressBar mLoadingIndicator;
    private MainActivity mParent;
    private TextView mEmptyText;
    @Inject
    public Service service;

    public FoodListFragment() {
        // Required empty public constructor
    }

    public static FoodListFragment newInstance(int position) {

        FoodListFragment fragment = new FoodListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mParent = (MainActivity)getActivity();
        mParent.getDependency().inject(this);
        mParent.currentFragmentTag = "FoodList";

        if (getArguments() != null) {
            mGridPosition = getArguments().getInt(ARG_PARAM1);
        }
        mPresenter = new FoodListPresenter(service, this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_food_list, container, false);
        mRecyclerView = (EmptyRecyclerView) view.findViewById(R.id.recycler_view);
        mEmptyText = view.findViewById(R.id.empty_view);
        mEmptyText.setVisibility(View.GONE);
        // The number of Columns
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.setEmptyView(emptyView);
        mLoadingIndicator = (ProgressBar) view.findViewById(R.id.loading_foodItems);

        mPresenter.start();
        return view;
    }

    @Override
    public void setPresenter(FoodListContract.Presenter presenter) {
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showWait() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        mLoadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        mEmptyText.setVisibility(View.VISIBLE);
        Toast.makeText(mParent.getApplicationContext(),"Network Error",Toast.LENGTH_SHORT).show();


    }

    @Override
    public void getFoodListSuccess(List<FoodAndDrinks> foodListResponse) {
        Log.d("Sushil", "FoodListFragment: getFoodListSuccess");
        mAdapter = new FoodAdapter(mParent.getApplicationContext(), foodListResponse.get(mGridPosition).getProducts(),
                new FoodAdapter.OnItemClickListener() {

                    @Override
                    public void onClick(View view, Product Item) {
                        Toast.makeText(mParent.getApplicationContext(), Item.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        mRecyclerView.setAdapter(mAdapter);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

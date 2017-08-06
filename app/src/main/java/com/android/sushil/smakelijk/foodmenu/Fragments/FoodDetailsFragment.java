package com.android.sushil.smakelijk.foodmenu.Fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.sushil.smakelijk.R;
import com.android.sushil.smakelijk.foodmenu.Activity.MainActivity;
import com.android.sushil.smakelijk.foodmenu.Contracts.FoodDetailsContract;
import com.android.sushil.smakelijk.foodmenu.Contracts.FoodListContract;
import com.android.sushil.smakelijk.foodmenu.Presenters.FoodDetailsPresenter;
import com.android.sushil.smakelijk.models.pojo.Product;
import com.android.sushil.smakelijk.util.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import javax.inject.Inject;


public class FoodDetailsFragment extends Fragment implements FoodDetailsContract.View {

    private static final String ARG_IMAGEURL = "imageUrl";
    private static final String ARG_NAME = "name";
    private static final String ARG_PRICE = "price";
    private static final String ARG_CURRENCY = "currency";

    private String mImageUrl;
    private String mName;
    private String mPrice;
    private String mCurrency;

    private ImageView mFoodImage;
    private TextView mFoodNameText;
    private TextView mPriceText;
    private TextView mCurrencyText;

    private FoodDetailsContract.Presenter mPresenter;
    private MainActivity mParent;

    private OnFragmentInteractionListener mListener;

    public FoodDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param imageUrl
     * @param name
     * @param price
     * @param currency
     * @return A new instance of fragment FoodDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodDetailsFragment newInstance(String imageUrl, String name, String price, String currency) {
        FoodDetailsFragment fragment = new FoodDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_IMAGEURL, imageUrl);
        args.putString(ARG_NAME, name);
        args.putString(ARG_PRICE, price);
        args.putString(ARG_CURRENCY, currency);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (MainActivity)getActivity();
        mParent.currentFragmentTag = "HomeScreen";

        if (getArguments() != null) {
            mImageUrl = getArguments().getString(ARG_IMAGEURL);
            mName = getArguments().getString(ARG_NAME);
            mPrice = getArguments().getString(ARG_PRICE);
            mCurrency = getArguments().getString(ARG_CURRENCY);
        }
        mPresenter = new FoodDetailsPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_food_details, container, false);
        mFoodImage = view.findViewById(R.id.img_thumbnail);
        mFoodNameText = view.findViewById(R.id.tv_name);
        mPriceText = view.findViewById(R.id.tv_price);
        mCurrencyText = view.findViewById(R.id.tv_currency);

        mFoodNameText.setText(mName);
        mPriceText.setText(mPrice);
        mCurrencyText.setText(mCurrency);

        String imageURL = Constants.API_URL + mImageUrl;
        Glide.with(getContext())
                .load(imageURL)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .error(R.drawable.default_image)
                .into(mFoodImage);

        mPresenter.start();
        return view;
    }

    @Override
    public void setPresenter(
            FoodListContract.Presenter presenter) {
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
    public void getFoodDetailsSuccess(Product productResponse) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

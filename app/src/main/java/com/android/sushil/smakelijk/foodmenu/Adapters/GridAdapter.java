package com.android.sushil.smakelijk.foodmenu.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.sushil.smakelijk.R;
import com.android.sushil.smakelijk.foodmenu.Activity.MainActivity;
import com.android.sushil.smakelijk.foodmenu.Fragments.FoodListFragment;
import com.android.sushil.smakelijk.foodmenu.Listener.ItemClickListener;
import com.android.sushil.smakelijk.foodmenu.Presenters.FoodListPresenter;
import com.android.sushil.smakelijk.util.ActivityUtils;
import com.android.sushil.smakelijk.util.EmptyRecyclerView;

import java.util.ArrayList;

/**
 * Created by sushiljha on 06/08/2017.
 */

public class GridAdapter extends EmptyRecyclerView.Adapter<GridAdapter.ViewHolder> {

    private final static String TAG = "GRID_ADAPTER";
    ArrayList<String> mName;
    ArrayList<Integer> mImage;
    Context mContext;

    public GridAdapter(Context context, ArrayList<String> alName, ArrayList<Integer> alImage) {
        super();
        Log.d(TAG, "GridAdapter Constructor");
        this.mContext = context;
        this.mName = alName;
        this.mImage = alImage;
    }

    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GridAdapter.ViewHolder viewHolder, int position) {
        Log.d(TAG, "onBindViewHolder");
        viewHolder.tvSpecies.setText(mName.get(position));
        viewHolder.imgThumbnail.setImageResource(mImage.get(position));

        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {

                } else {
                    FoodListFragment foodListFragment = (FoodListFragment) ((MainActivity) view.getContext()).getSupportFragmentManager().findFragmentByTag("FoodList");
                    if (foodListFragment == null) {
                        foodListFragment = foodListFragment.newInstance(position);

                        ActivityUtils.replaceFragmentToActivity(((MainActivity) view.getContext()).getSupportFragmentManager(),
                                foodListFragment, R.id.contentFrame, "FoodList");
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mName.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ImageView imgThumbnail;
        public TextView tvSpecies;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "ViewHolder Constructor");
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvSpecies = (TextView) itemView.findViewById(R.id.tv_species);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }
}

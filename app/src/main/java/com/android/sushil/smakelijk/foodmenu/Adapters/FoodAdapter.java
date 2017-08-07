package com.android.sushil.smakelijk.foodmenu.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.sushil.smakelijk.R;
import com.android.sushil.smakelijk.foodmenu.Activity.MainActivity;
import com.android.sushil.smakelijk.foodmenu.Fragments.FoodDetailsFragment;
import com.android.sushil.smakelijk.models.pojo.Product;
import com.android.sushil.smakelijk.util.ActivityUtils;
import com.android.sushil.smakelijk.util.Constants;
import com.android.sushil.smakelijk.util.EmptyRecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by sushiljha on 06/08/2017.
 */

public class FoodAdapter extends EmptyRecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private final static String TAG = "FOOD_ADAPTER";
    private final OnItemClickListener mListener;
    private List<Product> mData;
    private Context mContext;


    public FoodAdapter(Context context, List<Product> data, OnItemClickListener listener) {
        Log.d(TAG, "FoodAdapter : Constructor : data : " + data.size());
        this.mData = data;
        this.mListener = listener;
        this.mContext = context;
    }

    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item, parent, false);
        FoodAdapter.ViewHolder viewHolder = new FoodAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FoodAdapter.ViewHolder holder, int position) {
        holder.click(mData.get(position), mListener);
        holder.tvSpecies.setText(mData.get(position).getName());

        String imageName = mData.get(position).getUrl();
        String imageURL = Constants.API_URL + imageName;

        Glide.with(mContext)
                .load(imageURL)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .error(R.drawable.default_image)
                .into(holder.imgThumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgThumbnail;
        public TextView tvSpecies;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.d("Sushil", "ViewHolder Constructor");
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvSpecies = (TextView) itemView.findViewById(R.id.tv_species);
        }

        public void click(final Product foodListData, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FoodDetailsFragment foodDetailsFragment = (FoodDetailsFragment) ((MainActivity)view.getContext()).getSupportFragmentManager().findFragmentByTag("FoodDetails");
                    if (foodDetailsFragment == null) {
                        foodDetailsFragment = foodDetailsFragment.newInstance(foodListData.getUrl(),
                                                                              foodListData.getName(),
                                                                              foodListData.getSalePrice().getAmount(),
                                                                              foodListData.getSalePrice().getCurrency());
                    }
                    ActivityUtils.replaceFragmentToActivity(((MainActivity)view.getContext()).getSupportFragmentManager(),
                            foodDetailsFragment, R.id.contentFrame, "FoodDetails");
                    //listener.onClick(v, foodListData);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(View v, Product Item);
    }
}

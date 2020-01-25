package com.example.sofra.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.sofra.R;
import com.example.sofra.data.model.restaurantCategory.RestaurantCategory;
import com.example.sofra.data.model.restaurantCategory.RestaurantCategoryData;
import com.example.sofra.data.model.restaurantList.RestaurantData;
import com.example.sofra.data.model.userRestaurantReview.UserRestaurantReviewData;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.view.activity.BaseActivity;
import com.example.sofra.view.fragment.userCycle.userHome.userRestaurantMenu.UserItemDetailsFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantCategoryAdapter extends RecyclerView.Adapter<RestaurantCategoryAdapter.ViewHolder> {

    private BaseActivity activity;
    private List<RestaurantCategoryData> listRestaurantCategoryData = new ArrayList<>();
    RestaurantCategoryData restaurantCategoryData;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public RestaurantCategoryAdapter(BaseActivity activity, List<RestaurantCategoryData> listRestaurantCategoryData) {
        this.activity = activity;
        this.listRestaurantCategoryData = listRestaurantCategoryData;
//        viewBinderHelper.setOpenOnlyOne(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_restaurant_category,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        RestaurantCategoryData restaurantCategoryData = listRestaurantCategoryData.get(position);
//        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(restaurantCategoryData.getId()));

        Picasso.get().load(restaurantCategoryData.getPhotoUrl()).into(holder.itemRestaurantCategoryImgCategory);
        holder.itemRestaurantCategoryTvCategory.setText(restaurantCategoryData.getName());
    }

    private void setAction(ViewHolder holder, int position) {
        holder.position = position;
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    Toast.makeText(activity, "Opened", Toast.LENGTH_SHORT).show();
//                    UserItemDetailsFragment userItemDetailsFragment = new UserItemDetailsFragment();
//                    userItemDetailsFragment.restaurantItem = RestaurantItemData.get(position);
//                    HelperMethod.replace(userItemDetailsFragment, activity.getSupportFragmentManager(),
//                            R.id.user_cycle_fl_fragment_container, null, null);
                }
        });
    }

    @Override
    public int getItemCount() {
        return listRestaurantCategoryData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_restaurant_category_img_category)
        ImageView itemRestaurantCategoryImgCategory;
        @BindView(R.id.item_restaurant_category_tv_category)
        TextView itemRestaurantCategoryTvCategory;
        SwipeRevealLayout swipeRevealLayout;
        int position;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

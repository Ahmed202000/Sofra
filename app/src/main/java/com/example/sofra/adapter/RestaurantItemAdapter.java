package com.example.sofra.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sofra.R;
import com.example.sofra.data.model.listRestaurantItem.ListRestaurantItemData;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.view.activity.BaseActivity;
import com.example.sofra.view.fragment.userCycle.userHome.userRestaurantMenu.UserItemDetailsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantItemAdapter extends RecyclerView.Adapter<RestaurantItemAdapter.ViewHolder> {

    private BaseActivity activity;
    private List<ListRestaurantItemData> RestaurantItemData = new ArrayList<>();

    public RestaurantItemAdapter(BaseActivity activity, List<ListRestaurantItemData> restaurantItemData) {
        this.activity = activity;
        RestaurantItemData = restaurantItemData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_user_menu, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        ListRestaurantItemData listRestaurantItemData = RestaurantItemData.get(position);

        Glide.with(activity).load(listRestaurantItemData.getPhotoUrl()).into(holder.itemMenuImg);
        holder.itemMenuTvMealName.setText(listRestaurantItemData.getName());
        holder.itemMenuTvMealPrice.setText(listRestaurantItemData.getPrice());
        holder.itemMenuTvMealDetails.setText(listRestaurantItemData.getDescription());

    }

    private void setAction(ViewHolder holder, int position) {
        holder.position = position;
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Opened", Toast.LENGTH_SHORT).show();
                UserItemDetailsFragment userItemDetailsFragment = new UserItemDetailsFragment();
                userItemDetailsFragment.restaurantItem = RestaurantItemData.get(position);
                HelperMethod.replace(userItemDetailsFragment, activity.getSupportFragmentManager(),
                        R.id.user_cycle_activity_fl_container, null, null);

            }
        });
    }

    @Override
    public int getItemCount() {
        return RestaurantItemData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_menu_img)
        ImageView itemMenuImg;
        @BindView(R.id.item_menu_tv_meal_name)
        TextView itemMenuTvMealName;
        @BindView(R.id.item_menu_tv_meal_details)
        TextView itemMenuTvMealDetails;
        @BindView(R.id.item_menu_tv_meal_price)
        TextView itemMenuTvMealPrice;
        public int position;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

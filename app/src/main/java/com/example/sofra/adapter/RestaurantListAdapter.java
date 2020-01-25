package com.example.sofra.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sofra.R;
import com.example.sofra.data.model.restaurantList.RestaurantData;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.view.activity.BaseActivity;
import com.example.sofra.view.fragment.userCycle.userHome.userRestaurantMenu.UserRestaurantItemContainerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.ViewHolder> {


    private BaseActivity activity;
    RestaurantData restaurantData;
    private int lastPosition = -1;

    private List<RestaurantData> restaurantDataList = new ArrayList<>();

    public RestaurantListAdapter(BaseActivity activity, List<RestaurantData> restaurantDataList) {
        this.activity = activity;
        this.restaurantDataList = restaurantDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_user_restaurant_list,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
        setAnimation(holder.itemView, position,holder);

    }

    private void setAnimation(View viewToAnimate, int position,ViewHolder holder) {
        // If the bound view wasn't previously displayed on screen, it's animated
        // de 3shan lw 3ayz al animation mysht8lsh w ana tal3 tany
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(activity, R.anim.item_animation_fill_down);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    private void setData(ViewHolder holder, int position) {

        restaurantData = restaurantDataList.get(position);

        holder.itemRestaurantListTvRestaurantName.setText(restaurantData.getName());
        if (restaurantData.getActivated().equals("1")) {
            holder.itemRestaurantListImgOpened.setImageResource(R.drawable.shape_green_circle);
        } else {
            holder.itemRestaurantListImgOpened.setImageResource(R.drawable.shape_red_circle);
        }

        Glide.with(activity).load(restaurantData.getPhotoUrl()).into(holder.itemRestaurantListImgRestaurantLogo);
        holder.itemRestaurantListRbRating.setRating(restaurantData.getRate());
        holder.itemRestaurantListTvDeliveryFees.setText(restaurantData.getDeliveryCost());
        holder.itemRestaurantListTvMinimumOrder.setText(restaurantData.getMinimumCharger());
        holder.itemRestaurantListTvOpened.setText(restaurantData.getAvailability());

    }

    private void setAction(ViewHolder holder, int position) {
        holder.position = position;
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserRestaurantItemContainerFragment restaurantMenuFragment = new UserRestaurantItemContainerFragment();
                restaurantMenuFragment.restaurantData = restaurantDataList.get(position);
                HelperMethod.replace(restaurantMenuFragment, activity.getSupportFragmentManager(),
                        R.id.user_cycle_activity_fl_container, null, null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_restaurant_list_tv_restaurant_name)
        TextView itemRestaurantListTvRestaurantName;
        @BindView(R.id.item_restaurant_list_rb_rating)
        RatingBar itemRestaurantListRbRating;
        @BindView(R.id.item_restaurant_list_tv_minimum_order)
        TextView itemRestaurantListTvMinimumOrder;
        @BindView(R.id.item_restaurant_list_tv_delivery_fees)
        TextView itemRestaurantListTvDeliveryFees;
        @BindView(R.id.item_restaurant_list_img_opened)
        ImageView itemRestaurantListImgOpened;
        @BindView(R.id.item_restaurant_list_tv_opened)
        TextView itemRestaurantListTvOpened;
        @BindView(R.id.item_restaurant_list_img_restaurant_logo)
        CircleImageView itemRestaurantListImgRestaurantLogo;
        @BindView(R.id.container)
        RelativeLayout container;

        private View view;
        public int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

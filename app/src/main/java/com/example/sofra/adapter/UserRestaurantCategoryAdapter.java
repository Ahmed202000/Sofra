package com.example.sofra.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.sofra.R;
import com.example.sofra.data.model.listOfCategory.ListOfCategoryData;
import com.example.sofra.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserRestaurantCategoryAdapter extends RecyclerView.Adapter<UserRestaurantCategoryAdapter.ViewHolder> {

    private BaseActivity activity;
    private List<ListOfCategoryData> listOfCategoryDataList = new ArrayList<>();
    private ListOfCategoryData listOfCategoryData;

    public UserRestaurantCategoryAdapter(BaseActivity activity, List<ListOfCategoryData> listOfCategoryDataList) {
        this.activity = activity;
        this.listOfCategoryDataList = listOfCategoryDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_user_category_restaurant,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        listOfCategoryData = listOfCategoryDataList.get(position);

        holder.itemCategoryRestaurantTvTitle.setText(listOfCategoryData.getName());
        Glide.with(activity).load(listOfCategoryData.getPhotoUrl()).into(holder.itemCategoryRestaurantImgRestaurantLogo);

    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listOfCategoryDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_category_restaurant_img_restaurant_logo)
        CircleImageView itemCategoryRestaurantImgRestaurantLogo;
        @BindView(R.id.item_category_restaurant_tv_title)
        TextView itemCategoryRestaurantTvTitle;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

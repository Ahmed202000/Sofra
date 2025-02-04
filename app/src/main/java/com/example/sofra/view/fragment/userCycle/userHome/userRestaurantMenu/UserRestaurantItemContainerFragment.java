package com.example.sofra.view.fragment.userCycle.userHome.userRestaurantMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.sofra.R;
import com.example.sofra.adapter.ViewPagerFragmentAdapter;
import com.example.sofra.data.model.restaurantList.RestaurantData;
import com.example.sofra.view.fragment.untitledFolder.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserRestaurantItemContainerFragment extends BaseFragment {

    @BindView(R.id.menu_conatiner_fragment_tl)
    TabLayout menuConatinerFragmentTl;
    @BindView(R.id.menu_conatiner_fragment_vp)
    ViewPager menuConatinerFragmentVp;

    public RestaurantData restaurantData;

    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;

    public UserRestaurantItemContainerFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_user_menu_container, container, false);
        ButterKnife.bind(this, view);

        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager());

        UserRestaurantItemFragment userRestaurantItemFragment = new UserRestaurantItemFragment();
        userRestaurantItemFragment.restaurantData = restaurantData;

        UserRestaurantReviewFragment userRestaurantReviewFragment = new UserRestaurantReviewFragment();
        userRestaurantReviewFragment.restaurantData = restaurantData;

        UserRestaurantInformationFragment userRestaurantInformationFragment = new UserRestaurantInformationFragment();
        userRestaurantInformationFragment.restaurantData = restaurantData;

        viewPagerFragmentAdapter.addPager(userRestaurantItemFragment, "menu");
        viewPagerFragmentAdapter.addPager(userRestaurantReviewFragment, "review");
        viewPagerFragmentAdapter.addPager(userRestaurantInformationFragment, "information");

        menuConatinerFragmentVp.setAdapter(viewPagerFragmentAdapter);
        menuConatinerFragmentTl.setupWithViewPager(menuConatinerFragmentVp);

        return view;
    }


    @Override
    public void onBack() {
        super.onBack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

package com.example.sofra.view.fragment.resturantCycle.restaurantEditProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sofra.R;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.view.fragment.untitledFolder.BaseFragment;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class RestaurantEditProfileFragment1 extends BaseFragment {

    @BindView(R.id.restaurant_edit_profile1_fragment_add_photo)
    CircleImageView restaurantEditProfile1FragmentAddPhoto;
    @BindView(R.id.restaurant_edit_profile1_fragment_et_name)
    TextInputLayout restaurantEditProfile1FragmentEtName;
    @BindView(R.id.restaurant_edit_profile1_fragment_et_mail)
    TextInputLayout restaurantEditProfile1FragmentEtMail;
    @BindView(R.id.restaurant_edit_profile1_fragment_sp_governorate)
    Spinner restaurantEditProfile1FragmentSpGovernorate;
    @BindView(R.id.restaurant_edit_profile1_fragment_sp_city)
    Spinner restaurantEditProfile1FragmentSpCity;
    @BindView(R.id.restaurant_edit_profile1_fragment_et_minimum_delivery)
    TextInputLayout restaurantEditProfile1FragmentEtMinimumDelivery;
    @BindView(R.id.restaurant_edit_profile1_fragment_btn_continue)
    Button restaurantEditProfile1FragmentBtnContinue;

    public RestaurantEditProfileFragment1() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_restaurant_edit_profile1, container, false);
        ButterKnife.bind(this, view);


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

    @OnClick({R.id.restaurant_edit_profile1_fragment_et_minimum_delivery, R.id.restaurant_edit_profile1_fragment_btn_continue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.restaurant_edit_profile1_fragment_et_minimum_delivery:
                break;
            case R.id.restaurant_edit_profile1_fragment_btn_continue:

                HelperMethod.replace(new RestaurantEditProfileFragment2(), getActivity().getSupportFragmentManager(),
                        R.id.restaurant_cycle_fl_fragment_container, null, null);
                break;
        }
    }
}

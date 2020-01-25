package com.example.sofra.view.fragment.resturantCycle.restaurantLogin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sofra.R;
import com.example.sofra.helper.HelperMethod;
import com.example.sofra.view.fragment.untitledFolder.BaseFragment;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RestaurantRegisterFragment1 extends BaseFragment {

    @BindView(R.id.restaurant_register1_fragment_et_mail)
    TextInputEditText restaurantRegister1FragmentEtMail;
    @BindView(R.id.restaurant_register1_fragment_Sp_governorate)
    Spinner restaurantRegister1FragmentSpGovernorate;
    @BindView(R.id.register_fragment_sp_blood_type)
    ImageView registerFragmentSpBloodType;
    @BindView(R.id.restaurant_register1_fragment_Sp_city)
    Spinner restaurantRegister1FragmentSpCity;
    @BindView(R.id.restaurant_register1_fragment_et_delivery_duration)
    TextInputEditText restaurantRegister1FragmentEtDeliveryDuration;
    @BindView(R.id.restaurant_register1_fragment_et_password)
    TextInputEditText restaurantRegister1FragmentEtPassword;
    @BindView(R.id.restaurant_register1_fragment_et_password_confirmation)
    TextInputEditText restaurantRegister1FragmentEtPasswordConfirmation;
    @BindView(R.id.restaurant_register1_fragment_et_minimum_delivery)
    TextInputEditText restaurantRegister1FragmentEtMinimumDelivery;
    @BindView(R.id.restaurant_register1_fragment_et_delivery_cost)
    TextInputEditText restaurantRegister1FragmentEtDeliveryCost;
    @BindView(R.id.restaurant_register1_fragment_btn_continue)
    Button restaurantRegister1FragmentBtnContinue;

    public RestaurantRegisterFragment1() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_restaurant_register1, container, false);
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

    @OnClick(R.id.restaurant_register1_fragment_btn_continue)
    public void onViewClicked() {
        HelperMethod.replace(new RestaurantRegisterFragment2(), getActivity().getSupportFragmentManager(),
                R.id.restaurant_cycle_fl_fragment_container, null, null);
    }
}

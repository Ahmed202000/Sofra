package com.example.sofra.view.fragment.resturantCycle.resaurantMore.offers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sofra.R;
import com.example.sofra.view.fragment.untitledFolder.BaseFragment;

import butterknife.ButterKnife;

import static com.example.sofra.data.api.ApiClient.getClient;


public class RestaurantOffersFragment extends BaseFragment {

    public RestaurantOffersFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        getClient();

        View view = inflater.inflate(R.layout.fragment_restaurant_offers, container, false);
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
}

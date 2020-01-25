package com.example.sofra.view.fragment.userCycle.userHome.userRestaurantMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sofra.R;
import com.example.sofra.data.model.listOfCategory.ListOfCategory;
import com.example.sofra.data.model.restaurantList.RestaurantData;
import com.example.sofra.view.fragment.untitledFolder.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofra.data.api.ApiClient.getClient;


public class UserRestaurantInformationFragment extends BaseFragment {

    @BindView(R.id.restaurant_information_fragment_tv_case)
    TextView restaurantInformationFragmentTvCase;
    @BindView(R.id.restaurant_information_fragment_tv_city)
    TextView restaurantInformationFragmentTvCity;
    @BindView(R.id.restaurant_information_fragment_tv_town)
    TextView restaurantInformationFragmentTvTown;
    @BindView(R.id.restaurant_information_fragment_tv_minimum_cost)
    TextView restaurantInformationFragmentTvMinimumCost;
    @BindView(R.id.restaurant_information_fragment_tv_delivery_cost)
    TextView restaurantInformationFragmentTvDeliveryCost;

    public RestaurantData restaurantData;


    public UserRestaurantInformationFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_user_restaurant_information, container, false);
        ButterKnife.bind(this, view);
        getClient();

        init();
        return view;
    }

    private void init() {
        getClient().getUserCategory(restaurantData.getId()).enqueue(new Callback<ListOfCategory>() {
            @Override
            public void onResponse(Call<ListOfCategory> call, Response<ListOfCategory> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        restaurantInformationFragmentTvCase.setText(restaurantData.getAvailability());
//                        restaurantInformationFragmentTvCity.setText(restaurantData.getId());
//                        restaurantInformationFragmentTvTown.setText(restaurantData.getRegionId());
                        restaurantInformationFragmentTvMinimumCost.setText(restaurantData.getMinimumCharger());
                        restaurantInformationFragmentTvDeliveryCost.setText(restaurantData.getDeliveryCost());
                    }

                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<ListOfCategory> call, Throwable t) {

            }
        });
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

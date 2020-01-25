package com.example.sofra.data;

import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofra.R;
import com.example.sofra.adapter.RestaurantCategoryAdapter;
import com.example.sofra.adapter.RestaurantOrdersAdapter;
import com.example.sofra.adapter.SpinnerAdapter;
import com.example.sofra.data.local.SharedPreference;
import com.example.sofra.data.model.listOfCity.ListOfCity;
import com.example.sofra.data.model.listOfTown.ListOfTown;
import com.example.sofra.data.model.restaurantCategory.RestaurantCategory;
import com.example.sofra.data.model.restaurantCategory.RestaurantCategoryData;
import com.example.sofra.data.model.userOrders.UserOrders;
import com.example.sofra.data.model.userOrders.UserOrdersData;
import com.example.sofra.helper.OnEndLess;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.sofra.data.api.ApiClient.getClient;
import static com.example.sofra.data.local.SharedPreference.API_TOKEN;

public class GeneralRequest {

    @BindView(R.id.restaurant_order_container_fragment_rv_orders)
    RecyclerView restaurantOrderContainerFragmentRvOrders;
    @BindView(R.id.restaurant_category_fragment_rv_category)
    RecyclerView restaurantCategoryFragmentRvCategory;

    int maxPage = 0;
    public String State;

    RestaurantOrdersAdapter restaurantOrdersAdapter;
    RestaurantCategoryAdapter restaurantCategoryAdapter;
    private List<UserOrdersData> userOrdersData = new ArrayList<>();
    private List<RestaurantCategoryData> listOfCategoryDatalist = new ArrayList<>();

    public static void getSpinnerData(Call<ListOfCity> call, SpinnerAdapter spinnerAdapter, Spinner spinner, String hint) {

        call.enqueue(new Callback<ListOfCity>() {
            @Override
            public void onResponse(Call<ListOfCity> call, Response<ListOfCity> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        spinnerAdapter.setData(response.body().getData().getData(), hint);
                        spinner.setAdapter(spinnerAdapter);
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ListOfCity> call, Throwable t) {

            }
        });
    }

//    public void getRestaurantOrders(int page) {
//        getClient().getRestaurantOrders("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx", State, page).enqueue(new Callback<UserOrders>() {
//            @Override
//            public void onResponse(Call<UserOrders> call, Response<UserOrders> response) {
//                try {
//                    if (response.body().getStatus() == 1) {
//                        maxPage = response.body().getData().getLastPage();
//                        restaurantOrderContainerFragmentRvOrders.setVisibility(View.VISIBLE);
//                        userOrdersData.addAll(response.body().getData().getData());
//                        restaurantOrdersAdapter.notifyDataSetChanged();
//                    }
//                } catch (Exception e) {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserOrders> call, Throwable t) {
//
//            }
//        });
//    }

//    public void getRestaurantCategory(int page) {
////        String apiToken = SharedPreference.LoadData(getActivity(),API_TOKEN);
//
//        getClient().getRestaurantCategory("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx", page).enqueue(new Callback<RestaurantCategory>() {
//            @Override
//            public void onResponse(Call<RestaurantCategory> call, Response<RestaurantCategory> response) {
//                try {
//                    if (response.body().getStatus() == 1) {
//                        maxPage = response.body().getData().getLastPage();
//                        listOfCategoryDatalist.addAll(response.body().getData().getData());
//                        restaurantCategoryFragmentRvCategory.setVisibility(View.VISIBLE);
//                        restaurantCategoryAdapter.notifyDataSetChanged();
//                    }
//                } catch (Exception e) {
//                    Log.d(TAG, "onResponse: ");
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RestaurantCategory> call, Throwable t) {
//                Log.d(TAG, "onFailure: ");
//            }
//        });
//    }

//    public static void getSpinnerDataTown(Call<ListOfTown> call, SpinnerAdapter spinnerAdapter, Spinner spinner, String hint) {
//
//        call.enqueue(new Callback<ListOfTown>() {
//            @Override
//            public void onResponse(Call<ListOfTown> call, Response<ListOfTown> response) {
//
//                try {
//                    if (response.body().getStatus() == 1) {
//                        spinnerAdapter.setDataTown(response.body().getData().getData(), hint);
//                        spinner.setAdapter(spinnerAdapter);
//                    }
//                } catch (Exception e) {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ListOfTown> call, Throwable t) {
//
//            }
//        });
//    }

}

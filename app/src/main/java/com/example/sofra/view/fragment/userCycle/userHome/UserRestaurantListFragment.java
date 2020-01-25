package com.example.sofra.view.fragment.userCycle.userHome;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sofra.R;
import com.example.sofra.adapter.RestaurantListAdapter;
import com.example.sofra.adapter.SpinnerAdapter;
import com.example.sofra.data.GeneralRequest;
import com.example.sofra.data.model.restaurantList.RestaurantData;
import com.example.sofra.data.model.restaurantList.RestaurantList;
import com.example.sofra.helper.OnEndLess;
import com.example.sofra.view.activity.BaseActivity;
import com.example.sofra.view.fragment.untitledFolder.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sofra.data.api.ApiClient.getClient;


public class UserRestaurantListFragment extends BaseFragment {

    @BindView(R.id.restaurant_list_fragment_et_city)
    EditText restaurantListFragmentEtCity;
    @BindView(R.id.restaurant_list_fragment_sp_city)
    Spinner restaurantListFragmentSpCity;
    @BindView(R.id.restaurant_list_fragment_rv_restaurant)
    RecyclerView restaurantListFragmentRvRestaurant;
    @BindView(R.id.restaurant_list_fragment_pb_loading)
    ProgressBar restaurantListFragmentPbLoading;
    @BindView(R.id.restaurant_list_fragment_swipe_refresh)
    SwipeRefreshLayout restaurantListFragmentSwipeRefresh;
    @BindView(R.id.pagination_progress)
    ProgressBar paginationProgress;

    private RestaurantListAdapter restaurantListAdapter;
    private List<RestaurantData> ListRestaurantData = new ArrayList<>();
    private OnEndLess onEndLess;
    private boolean FILTER = false;
    private int maxPage = 0;
    private SpinnerAdapter citySpinnerAdapter;
    private LinearLayoutManager linearLayoutManager;


    public UserRestaurantListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_user_restaurant_list, container, false);
        ButterKnife.bind(this, view);
        restaurantListFragmentSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        restaurantListFragmentSwipeRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        init();
        return view;
    }

    public void init() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        restaurantListFragmentRvRestaurant.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getRestaurantList(current_page);
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                }
            }
        };
        restaurantListFragmentRvRestaurant.addOnScrollListener(onEndLess);

        restaurantListAdapter = new RestaurantListAdapter((BaseActivity) getActivity(), ListRestaurantData);
        restaurantListFragmentRvRestaurant.setAdapter(restaurantListAdapter);

        citySpinnerAdapter = new SpinnerAdapter(getActivity());
        GeneralRequest.getSpinnerData(getClient().getCity(), citySpinnerAdapter
                , restaurantListFragmentSpCity, "City");

        getRestaurantList(1);
    }

    private void getRestaurantList(int page) {

        getClient().getRestaurant(page).enqueue(new Callback<RestaurantList>() {
            @Override
            public void onResponse(Call<RestaurantList> call, Response<RestaurantList> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        maxPage = response.body().getData().getLastPage();
                        ListRestaurantData.addAll(response.body().getData().getData());
                        restaurantListFragmentPbLoading.setVisibility(View.GONE);
                        restaurantListAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<RestaurantList> call, Throwable t) {

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

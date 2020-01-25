package com.example.sofra.view.fragment.userCycle.userHome.userRestaurantMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofra.R;
import com.example.sofra.adapter.RestaurantItemAdapter;
import com.example.sofra.adapter.UserRestaurantCategoryAdapter;
import com.example.sofra.data.model.listOfCategory.ListOfCategory;
import com.example.sofra.data.model.listOfCategory.ListOfCategoryData;
import com.example.sofra.data.model.listRestaurantItem.ListRestaurantItem;
import com.example.sofra.data.model.listRestaurantItem.ListRestaurantItemData;
import com.example.sofra.data.model.restaurantList.RestaurantData;
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


public class UserRestaurantItemFragment extends BaseFragment {

    public RestaurantData restaurantData;

    @BindView(R.id.restaurant_menu_fragment_rv_menu)
    RecyclerView restaurantMenuFragmentRvMenu;
    @BindView(R.id.restaurant_menu_fragment_rv_category)
    RecyclerView restaurantMenuFragmentRvCategory;
    @BindView(R.id.user_restaurant_fragment_pb_loading)
    ProgressBar userRestaurantFragmentPbLoading;

    private int maxPage = 0;
    private LinearLayoutManager linearLayoutManager;
    private OnEndLess onEndLess;
    private List<ListRestaurantItemData> listRestaurantItemData = new ArrayList<>();
    private List<ListOfCategoryData> listOfCategoryDataList = new ArrayList<>();
    private RestaurantItemAdapter restaurantItemAdapter;
    public int id = -1;
    private UserRestaurantCategoryAdapter userRestaurantCategoryAdapter;

    public UserRestaurantItemFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        getClient();
        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_user_restaurant_item, container, false);
        ButterKnife.bind(this, view);

        restaurantMenuFragmentRvCategory.setVisibility(View.GONE);
        restaurantMenuFragmentRvMenu.setVisibility(View.GONE);
        menuInit();
        categoryInit();
        return view;
    }

    private void categoryInit() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        restaurantMenuFragmentRvCategory.setLayoutManager(linearLayoutManager);

        userRestaurantCategoryAdapter = new UserRestaurantCategoryAdapter((BaseActivity) getActivity(), listOfCategoryDataList);
        restaurantMenuFragmentRvCategory.setAdapter(userRestaurantCategoryAdapter);
        getCategoryList();

    }

    public void menuInit() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        restaurantMenuFragmentRvMenu.setLayoutManager(linearLayoutManager);

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
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        restaurantMenuFragmentRvMenu.addOnScrollListener(onEndLess);

        restaurantItemAdapter = new RestaurantItemAdapter((BaseActivity) getActivity(), listRestaurantItemData);
        restaurantMenuFragmentRvMenu.setAdapter(restaurantItemAdapter);

        getRestaurantList(1);
    }

    private void getCategoryList() {
        getClient().getUserCategory(restaurantData.getId()).enqueue(new Callback<ListOfCategory>() {
            @Override
            public void onResponse(Call<ListOfCategory> call, Response<ListOfCategory> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        listOfCategoryDataList.addAll(response.body().getData());
                        restaurantMenuFragmentRvCategory.setVisibility(View.VISIBLE);
                        userRestaurantCategoryAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                }
            }
            @Override
            public void onFailure(Call<ListOfCategory> call, Throwable t) {
                try {

                } catch (Exception e) {
                }
            }
        });
    }

    public void getRestaurantList(int page) {
        getClient().getRestaurantItem(restaurantData.getId(), id, page).
                enqueue(new Callback<ListRestaurantItem>() {
                    @Override
                    public void onResponse(Call<ListRestaurantItem> call, Response<ListRestaurantItem> response) {
                        try {
                            if (response.body().getStatus() == 1) {
//                                if (page == 1) {
//                                    listRestaurantItemData = new ArrayList<>();
//                                    maxPage = 0;
//                                    onEndLess.current_page = 1;
//                                    onEndLess.previous_page = 0;
//                                    onEndLess.totalItemCount = 0;
//                                    onEndLess.visibleItemCount = 0;
//                                }

                                maxPage = response.body().getData().getLastPage();
                                listRestaurantItemData.addAll(response.body().getData().getData());

                                restaurantMenuFragmentRvMenu.setVisibility(View.VISIBLE);
                                restaurantItemAdapter.notifyDataSetChanged();
                            }

                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<ListRestaurantItem> call, Throwable t) {
                        try {

                        } catch (Exception e) {

                        }
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

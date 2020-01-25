package com.example.sofra.view.fragment.resturantCycle.restaurantHome;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofra.R;
import com.example.sofra.adapter.RestaurantCategoryAdapter;
import com.example.sofra.data.GeneralRequest;
import com.example.sofra.data.model.restaurantCategory.RestaurantCategory;
import com.example.sofra.data.model.restaurantCategory.RestaurantCategoryData;
import com.example.sofra.helper.OnEndLess;
import com.example.sofra.view.activity.BaseActivity;
import com.example.sofra.view.activity.RestaurantCycleActivity;
import com.example.sofra.view.fragment.untitledFolder.BaseFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.sofra.data.api.ApiClient.getClient;


public class RestaurantCategoryFragment extends BaseFragment {

    @BindView(R.id.restaurant_category_fragment_rv_category)
    RecyclerView restaurantCategoryFragmentRvCategory;
    @BindView(R.id.restaurant_list_fragment_btn_float)
    FloatingActionButton restaurantListFragmentBtnFloat;


    private GeneralRequest generalRequest = new GeneralRequest();

    private static final int PERMISSION_REQUEST = 0;
    private static final int RESULT_LOAD_IMAGE = 1;
    private LinearLayoutManager linearLayoutManager;
    private RestaurantCategoryAdapter restaurantCategoryAdapter;

    private List<RestaurantCategoryData> listOfCategoryDatalist = new ArrayList<>();
    private OnEndLess onEndLess;
    private int maxPage = 0;
    View view;

    public RestaurantCategoryFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_restaurant_category, container, false);
        ButterKnife.bind(this, view);

        RestaurantCycleActivity restaurantCycleActivity = (RestaurantCycleActivity) getActivity();
        restaurantCycleActivity.setVisibilityBottomNavBar(View.VISIBLE);
        restaurantCycleActivity.setVisibilityToolBar(View.VISIBLE);

        restaurantCategoryFragmentRvCategory.setVisibility(View.GONE);
        init();
        floatHidden();

        return view;
    }

    private void floatHidden() {
        restaurantCategoryFragmentRvCategory.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

//                BottomNavigationView bottomNavigationView = view.findViewById(R.id.activity_restaurant_bottom_nav_bar);
//                if (dy > 0 && bottomNavigationView.isShown()) {
//                    bottomNavigationView.setVisibility(View.GONE);
//                } else if (dy < 0) {
//                    bottomNavigationView.setVisibility(View.VISIBLE);
//                }

                if (dy > 0 && restaurantListFragmentBtnFloat.getVisibility() == View.VISIBLE) {
                    restaurantListFragmentBtnFloat.hide();
                } else if (dy < 0 && restaurantListFragmentBtnFloat.getVisibility() != View.VISIBLE) {
                    restaurantListFragmentBtnFloat.show();
                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        restaurantListFragmentBtnFloat.show();
    }

    private void init() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        restaurantCategoryFragmentRvCategory.setLayoutManager(linearLayoutManager);
        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getRestaurantCategory(current_page);
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                }
            }
        };
        restaurantCategoryFragmentRvCategory.addOnScrollListener(onEndLess);

        restaurantCategoryAdapter = new RestaurantCategoryAdapter((BaseActivity) getActivity(), listOfCategoryDatalist);
        restaurantCategoryFragmentRvCategory.setAdapter(restaurantCategoryAdapter);
        getRestaurantCategory(1);
    }

    private void getRestaurantCategory(int page) {
//        String apiToken = SharedPreference.LoadData(getActivity(),API_TOKEN);

        getClient().getRestaurantCategory("Jptu3JVmDXGpJEaQO9ZrjRg5RuAVCo45OC2AcOKqbVZPmu0ZJPN3T1sm0cWx", page).enqueue(new Callback<RestaurantCategory>() {
            @Override
            public void onResponse(Call<RestaurantCategory> call, Response<RestaurantCategory> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        maxPage = response.body().getData().getLastPage();
                        listOfCategoryDatalist.addAll(response.body().getData().getData());
                        restaurantCategoryFragmentRvCategory.setVisibility(View.VISIBLE);
                        restaurantCategoryAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Log.d(TAG, "onResponse: ");

                }
            }

            @Override
            public void onFailure(Call<RestaurantCategory> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    private void dialogLogOut() {
        Dialog dialog = new Dialog(getActivity());
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_restaurant_add_category, null);
        dialog.setContentView(v);
        CircleImageView circleImageView = (CircleImageView) dialog.findViewById(R.id.item_restaurant_add_category_dialog_img_add_photo);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGallery();
            }
        });
        Button btn_done = (Button) dialog.findViewById(R.id.item_restaurant_add_category_dialog_btn_add_category);
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "added category", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    private void initGallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(baseActivity, "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(baseActivity, "Permission not granted", Toast.LENGTH_SHORT).show();
                    baseActivity.finish();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case RESULT_LOAD_IMAGE:
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();
                    CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.item_restaurant_add_category_dialog_img_add_photo);
                    circleImageView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    break;

            }
    }

    @OnClick(R.id.restaurant_list_fragment_btn_float)
    public void onViewClicked() {
        dialogLogOut();
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

package com.example.sofra.data.api;


import com.example.sofra.data.model.listOfCity.ListOfCity;
import com.example.sofra.data.model.listOfCategory.ListOfCategory;
import com.example.sofra.data.model.listOfTown.ListOfTown;
import com.example.sofra.data.model.listRestaurantItem.ListRestaurantItem;
import com.example.sofra.data.model.restaurantCategory.RestaurantCategory;
import com.example.sofra.data.model.restaurantCommission.RestaurantCommission;
import com.example.sofra.data.model.restaurantList.RestaurantList;
import com.example.sofra.data.model.restaurantLogin.RestaurantLogin;
import com.example.sofra.data.model.userDetailsListOffer.UserDetailsListOffer;
import com.example.sofra.data.model.userListOffer.UserListOffer;
import com.example.sofra.data.model.userOrders.UserOrders;
import com.example.sofra.data.model.userRestaurantReview.UserRestaurantReview;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("cities")
    Call<ListOfCity> getCity();

    @GET("regions")
    Call<ListOfTown> getTown(@Query("city_id") int cityId);

    @GET("categories")
    Call<ListOfCategory> getUserCategory(@Query("restaurant_id") int restaurantId);

    @GET("client/my-orders")
    Call<UserOrders> getUserOrders(@Query("api_token") String apiToken,
                                   @Query("state") String state,
                                   @Query("page") int page);

    @GET("restaurant/my-orders")
    Call<UserOrders> getRestaurantOrders(@Query("api_token") String apiToken,
                                         @Query("state") String state,
                                         @Query("page") int page);

    @GET("items")
    Call<ListRestaurantItem> getRestaurantItem(@Query("restaurant_id") int restaurantId,
                                               @Query("category_id") int categoryId,
                                               @Query("page") int page);

    @GET("restaurants")
    Call<RestaurantList> getRestaurant(@Query("page") int page);

    @GET("restaurant/my-categories")
    Call<RestaurantCategory> getRestaurantCategory(@Query("api_token") String apiToken, @Query("page") int page);

    @GET("restaurant/reviews")
    Call<UserRestaurantReview> getUserRestaurantReview(@Query("api_token") String apiToken,
                                                       @Query("restaurant_id") int restaurantId,
                                                       @Query("page") int page);

    @POST("restaurant/login")
    @FormUrlEncoded
    Call<RestaurantLogin> getRestaurantLogin(@Field("email") String emial,
                                             @Field("password") String password);

    @GET("restaurant/commissions")
    Call<RestaurantCommission> getRestaurantCommission(@Query("api_token") String apiToken);


    @GET("offer")
    Call<UserDetailsListOffer> getOfferDetails(@Query("offer_id") int offerId);

    @GET("offers")
    Call<UserListOffer> getListOffer(@Query("page") int page);

}

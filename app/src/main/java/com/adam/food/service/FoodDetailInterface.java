package com.adam.food.service;

import com.adam.food.domain.fooddetail.FoodDetail;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by adamlee on 2016/3/17.
 */
public interface FoodDetailInterface {
    @GET("api/food/show")
    Observable<FoodDetail> getFoodDetail(@Query("id") int id);

}

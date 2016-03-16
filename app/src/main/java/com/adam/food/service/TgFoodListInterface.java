package com.adam.food.service;

import com.adam.food.domain.foodlist.TgFoodListWrapper;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by adamlee on 2016/3/15.
 */
public interface TgFoodListInterface {
    /**
     * http://www.tngou.net/api/food/list?id=3&rows=10&page=1
     * @return
     */
@GET("api/food/list")
    Observable<TgFoodListWrapper> getTgList(@Query("id") int id,@Query("rows") int rows,@Query("page") int page);
}

package com.adam.food.model.fooddetail;

import com.adam.food.domain.fooddetail.FoodDetail;

/**
 * Created by adamlee on 2016/3/17.
 */
public interface OnfoodDetailListener {
    void before();
    void error(Throwable throwable);
    void success(FoodDetail foodDetail);
    void after();
}

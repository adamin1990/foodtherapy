package com.adam.food.model.fooddetail;

/**
 * Created by adamlee on 2016/3/17.
 */
public interface FoodDetailModel {
    void getDetail(int id, String what, OnfoodDetailListener onfoodDetailListener);
}

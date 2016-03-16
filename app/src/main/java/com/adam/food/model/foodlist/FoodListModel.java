package com.adam.food.model.foodlist;

/**
 * Created by adamlee on 2016/3/15.
 */
public interface FoodListModel {
    void getFoodList(int id,int rows,int page,OnFoodListener onFoodListener);
}

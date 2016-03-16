package com.adam.food.view.list.foodlist;

import com.adam.food.domain.foodlist.TgFoodListWrapper;

/**
 * Created by adamlee on 2016/3/16.
 */
public interface FoodListView {
    void showLoading();
    void hideLoading();
    void showError(Throwable e);
    void setData(TgFoodListWrapper tgFoodListWrapper);
}

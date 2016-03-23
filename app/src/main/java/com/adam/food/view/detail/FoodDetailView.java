package com.adam.food.view.detail;

import com.adam.food.domain.fooddetail.FoodDetail;

/**
 * Created by adamlee on 2016/3/17.
 */
public interface FoodDetailView {
    void showLoading();
    void hideLoading();
    void setData(FoodDetail foodDetail);
    void showError(Throwable throwable);
}

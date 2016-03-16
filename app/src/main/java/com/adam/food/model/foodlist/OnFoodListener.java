package com.adam.food.model.foodlist;

import com.adam.food.domain.foodlist.TgFoodListWrapper;

/**
 * Created by adamlee on 2016/3/15.
 */
public interface OnFoodListener {
    void before();
    void onSuccess(TgFoodListWrapper tgFoodListWrapper);
    void error(Throwable throwable);
    void complete();
}

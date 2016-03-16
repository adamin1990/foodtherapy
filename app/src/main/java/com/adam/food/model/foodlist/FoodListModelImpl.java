package com.adam.food.model.foodlist;

import com.adam.food.domain.foodlist.TgFoodListWrapper;
import com.adam.food.service.TgFoodListInterface;
import com.adam.food.utils.ServiceGenerator;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by adamlee on 2016/3/15.
 */
public class FoodListModelImpl implements FoodListModel {
    @Override
    public void getFoodList(int id, int rows, int page, final OnFoodListener onFoodListener) {
        onFoodListener.before();
        ServiceGenerator.createService(TgFoodListInterface.class)
                .getTgList(id, rows, page)
                .observeOn(AndroidSchedulers.mainThread())
//                .doOnRequest(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        onFoodListener.before();
//                    }
//                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<TgFoodListWrapper>() {
                    @Override
                    public void onCompleted() {
                        onFoodListener.complete();

                    }

                    @Override
                    public void onError(Throwable e) {
                        onFoodListener.complete();
                        onFoodListener.error(e);

                    }

                    @Override
                    public void onNext(TgFoodListWrapper tgFoodListWrapper) {
                        onFoodListener.onSuccess(tgFoodListWrapper);
                    }
                });
    }
}

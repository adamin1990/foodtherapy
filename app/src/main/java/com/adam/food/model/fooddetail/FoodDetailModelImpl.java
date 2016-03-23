package com.adam.food.model.fooddetail;

import com.adam.food.domain.fooddetail.FoodDetail;
import com.adam.food.service.FoodDetailInterface;
import com.adam.food.utils.ServiceGenerator;

import adamin.toolkits.utils.LogUtil;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by adamlee on 2016/3/17.
 */
public class FoodDetailModelImpl implements FoodDetailModel {
    @Override
    public void getDetail(int id, String what, final OnfoodDetailListener onfoodDetailListener) {
        onfoodDetailListener.before();
        LogUtil.error(FoodDetailModelImpl.class,"执行showloading");
        ServiceGenerator.createService(FoodDetailInterface.class)
                .getFoodDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FoodDetail>() {
                    @Override
                    public void onCompleted() {
                        onfoodDetailListener.after();

                    }

                    @Override
                    public void onError(Throwable e) {
                        onfoodDetailListener.after();
                        onfoodDetailListener.error(e);

                    }

                    @Override
                    public void onNext(FoodDetail foodDetail) {
                        onfoodDetailListener.after();
                        onfoodDetailListener.success(foodDetail);

                    }
                });

    }
}

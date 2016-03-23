package com.adam.food.presenter.detail;

import com.adam.food.domain.fooddetail.FoodDetail;
import com.adam.food.model.fooddetail.FoodDetailModel;
import com.adam.food.model.fooddetail.FoodDetailModelImpl;
import com.adam.food.model.fooddetail.OnfoodDetailListener;
import com.adam.food.view.detail.FoodDetailView;

/**
 * Created by adamlee on 2016/3/17.
    */
    public class FoodDetailPresenter implements OnfoodDetailListener {
    private FoodDetailView foodDetailView;
    private FoodDetailModel foodDetailModel;

    public FoodDetailPresenter(FoodDetailView foodDetailView) {
        this.foodDetailView = foodDetailView;
        foodDetailModel=new FoodDetailModelImpl();
    }

    public void getFooodDetai(int id){
        foodDetailModel.getDetail(id,"",this);
    }

    @Override
    public void before() {
        foodDetailView.showLoading();

    }

    @Override
    public void error(Throwable throwable) {
        foodDetailView.hideLoading();
        foodDetailView.showError(throwable);

    }

    @Override
    public void success(FoodDetail foodDetail) {
        foodDetailView.setData(foodDetail);

    }

    @Override
    public void after() {
        foodDetailView.hideLoading();

    }
}

package com.adam.food.presenter.list;

import com.adam.food.domain.foodlist.TgFoodListWrapper;
import com.adam.food.model.foodlist.FoodListModel;
import com.adam.food.model.foodlist.FoodListModelImpl;
import com.adam.food.model.foodlist.OnFoodListener;
import com.adam.food.view.list.foodlist.FoodListView;

/**
 * Created by adamlee on 2016/3/16.
 */
public class FoodListPresenter implements OnFoodListener {
    private FoodListModel foodListModel;
    private FoodListView foodListView;

    public FoodListPresenter(FoodListView foodListView) {
        this.foodListView = foodListView;
        foodListModel=new FoodListModelImpl();
    }
    public void getFoodList(int id,int rows,int page){
        foodListModel.getFoodList(id,rows,page,this);
    }

    @Override
    public void before() {
        foodListView.showLoading();

    }

    @Override
    public void onSuccess(TgFoodListWrapper tgFoodListWrapper) {
        foodListView.setData(tgFoodListWrapper);

    }

    @Override
    public void error(Throwable throwable) {
        foodListView.showError(throwable);

    }

    @Override
    public void complete() {
        foodListView.hideLoading();

    }
}

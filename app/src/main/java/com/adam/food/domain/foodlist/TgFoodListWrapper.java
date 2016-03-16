package com.adam.food.domain.foodlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adamlee on 2016/3/15.
 */
public class TgFoodListWrapper {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("tngou")
    @Expose
    private List<TgFoodList> tngou = new ArrayList<TgFoodList>();

    /**
     *
     * @return
     * The status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     *
     * @param total
     * The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     *
     * @return
     * The tngou
     */
    public List<TgFoodList> getTngou() {
        return tngou;
    }

    /**
     *
     * @param tngou
     * The tngou
     */
    public void setTngou(List<TgFoodList> tngou) {
        this.tngou = tngou;
    }
}

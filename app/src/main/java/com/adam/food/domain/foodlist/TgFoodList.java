package com.adam.food.domain.foodlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by adamlee on 2016/3/15.
 */
public class TgFoodList {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("disease")
    @Expose
    private String disease;
    @SerializedName("fcount")
    @Expose
    private Integer fcount;
    @SerializedName("food")
    @Expose
    private String food;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("keywords")
    @Expose
    private String keywords;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rcount")
    @Expose
    private Integer rcount;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("symptom")
    @Expose
    private String symptom;

    /**
     *
     * @return
     * The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     *
     * @param count
     * The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The disease
     */
    public String getDisease() {
        return disease;
    }

    /**
     *
     * @param disease
     * The disease
     */
    public void setDisease(String disease) {
        this.disease = disease;
    }

    /**
     *
     * @return
     * The fcount
     */
    public Integer getFcount() {
        return fcount;
    }

    /**
     *
     * @param fcount
     * The fcount
     */
    public void setFcount(Integer fcount) {
        this.fcount = fcount;
    }

    /**
     *
     * @return
     * The food
     */
    public String getFood() {
        return food;
    }

    /**
     *
     * @param food
     * The food
     */
    public void setFood(String food) {
        this.food = food;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The img
     */
    public String getImg() {
        return img;
    }

    /**
     *
     * @param img
     * The img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     *
     * @return
     * The keywords
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     *
     * @param keywords
     * The keywords
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The rcount
     */
    public Integer getRcount() {
        return rcount;
    }

    /**
     *
     * @param rcount
     * The rcount
     */
    public void setRcount(Integer rcount) {
        this.rcount = rcount;
    }

    /**
     *
     * @return
     * The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     *
     * @param summary
     * The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     *
     * @return
     * The symptom
     */
    public String getSymptom() {
        return symptom;
    }

    /**
     *
     * @param symptom
     * The symptom
     */
    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

}

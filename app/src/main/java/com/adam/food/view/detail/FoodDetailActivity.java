package com.adam.food.view.detail;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.adam.food.R;
import com.adam.food.base.BaseActivity;
import com.adam.food.base.Constant;
import com.adam.food.domain.fooddetail.FoodDetail;
import com.adam.food.presenter.detail.FoodDetailPresenter;
import com.iflytek.voiceads.AdError;
import com.iflytek.voiceads.IFLYAdListener;
import com.iflytek.voiceads.IFLYAdSize;
import com.iflytek.voiceads.IFLYBannerAd;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import adamin.toolkits.utils.ColorUtils;
import adamin.toolkits.utils.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class FoodDetailActivity extends BaseActivity implements FoodDetailView{
     @Bind(R.id.collapsingtoolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.image)
    ImageView imageView;
    @Bind(R.id.webview)
    WebView webView;

    private int id;
    private String name;
    FoodDetailPresenter foodDetailPresenter;
    private AlertDialog dialog;


    private LinearLayout layout_ads;
    private IFLYBannerAd bannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);
        init();

    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name=getIntent().getStringExtra("name");
        id=getIntent().getIntExtra("id",1);
        collapsingToolbarLayout.setTitle(name);
        getSupportActionBar().setTitle(name+"");
        dialog= DialogUtil.buildLoadingDialog(FoodDetailActivity.this);
        foodDetailPresenter=new FoodDetailPresenter(this);

        createBannerAd();
        foodDetailPresenter.getFooodDetai(id);

    }

    public void createBannerAd() {
        //此广告位为Demo专用，广告的展示不产生费用
        String adUnitId = "6D0F2105F94DC79AB0DFDB78180F3791";
        //创建旗帜广告，传入广告位ID
        bannerView = IFLYBannerAd.createBannerAd(this, adUnitId);
        //设置请求的广告尺寸
        bannerView.setAdSize(IFLYAdSize.BANNER);
        //设置下载广告前，弹窗提示
//		bannerView.setParameter(AdKeys.DOWNLOAD_ALERT, "true");

        //请求广告，添加监听器
        bannerView.loadAd(mAdListener);
        //将广告添加到布局
        layout_ads = (LinearLayout)findViewById(R.id.layout_adview);
        layout_ads.removeAllViews();
        layout_ads.addView(bannerView);

    }

    @Override
    public void showLoading() {
        dialog.show();

    }

    @Override
    public void hideLoading() {
        dialog.dismiss();

    }

    @Override
    public void setData(final FoodDetail foodDetail) {
        Picasso.with(FoodDetailActivity.this)
                .load(Constant.TGIMG_PREFIX+foodDetail.getImg())
                .placeholder(new ColorDrawable(ColorUtils.getRandomColor(FoodDetailActivity.this)))
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        webView.loadDataWithBaseURL(null,foodDetail.getMessage()+"","text/html","utf-8",null);
                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    IFLYAdListener mAdListener = new IFLYAdListener(){

        /**
         * 广告请求成功
         */
        @Override
        public void onAdReceive() {
            //展示广告
            bannerView.showAd();

        }

        /**
         * 广告请求失败
         */
        @Override
        public void onAdFailed(AdError error) {
        }

        /**
         * 广告被点击
         */
        @Override
        public void onAdClick() {
        }

        /**
         * 广告被关闭
         */
        @Override
        public void onAdClose() {
        }

        @Override
        public void onAdExposure() {
            // TODO Auto-generated method stub

        }
    };

    @Override
    protected void onDestroy() {
        if(dialog!=null){
            hideLoading();
        }
        super.onDestroy();
    }
}

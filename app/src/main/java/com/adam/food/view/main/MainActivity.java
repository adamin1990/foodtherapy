package com.adam.food.view.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.adam.food.R;
import com.adam.food.adapter.MainPagerAdapter;
import com.adam.food.base.BaseActivity;
import com.adam.food.domain.TgClassify;
import com.adam.food.domain.TgClassifyWrapper;
import com.adam.food.presenter.main.ClassifyPresenter;
import com.adam.food.utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.List;

import adamin.toolkits.utils.DialogUtil;
import adamin.toolkits.utils.LogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.pager_food)
    ViewPager pagerfood;
    @Bind(R.id.pager_receipe)
    ViewPager pagerreceipe;
    @Bind(R.id.tabfood)
    TabLayout tabfood;
    @Bind(R.id.tabreceipe)
    TabLayout tabreceipe;
    @Bind(R.id.radio_group)
    RadioGroup radioGroup;
    private MainPagerAdapter foodpageradapter;
    private MainPagerAdapter receipepageradapter;
    private boolean first = true;

    private ClassifyPresenter presenter;
    AlertDialog alertDialog;
    private List<TgClassify> foods;
    private List<TgClassify> receipies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        init();

    }

    @Override
    protected void init() {
        alertDialog = DialogUtil.buildCustomDialog(MainActivity.this, "加载中");
        presenter = new ClassifyPresenter(this);
        presenter.getClassify("food", 0);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        foods = new ArrayList<>();
        receipies = new ArrayList<>();
        foodpageradapter = new MainPagerAdapter(getSupportFragmentManager(), foods, "food");
        receipepageradapter = new MainPagerAdapter(getSupportFragmentManager(), receipies, "cook");
        pagerfood.setAdapter(foodpageradapter);
        pagerreceipe.setAdapter(receipepageradapter);
        tabfood.setupWithViewPager(pagerfood);
        tabreceipe.setupWithViewPager(pagerreceipe);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_1:
                        tabfood.setVisibility(View.VISIBLE);
                        pagerfood.setVisibility(View.VISIBLE);
                        tabreceipe.setVisibility(View.GONE);
                        pagerreceipe.setVisibility(View.GONE);
                        break;
                    case R.id.radio_2:
                        tabfood.setVisibility(View.GONE);
                        pagerfood.setVisibility(View.GONE);
                        tabreceipe.setVisibility(View.VISIBLE);
                        pagerreceipe.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showLoading() {
        alertDialog.show();

    }

    @Override
    public void hideLoaidng() {
        alertDialog.dismiss();

    }

    @Override
    public void showError(Throwable throwable) {
        SnackBarUtils.showSnackBar(MainActivity.this, "出错了" + throwable.getMessage(), SnackBarUtils.ERROR);

    }

    @Override
    public void setData(TgClassifyWrapper tgClassifyWrapper, String name) {
        if (first) {
            first = false;
            foods.addAll(tgClassifyWrapper.getTngou());
            foodpageradapter.notifyDataSetChanged();
            presenter.getClassify("cook", 0);
        } else {
            receipies.addAll(tgClassifyWrapper.getTngou());
            receipepageradapter.notifyDataSetChanged();

        }
        LogUtil.error(MainActivity.class, tgClassifyWrapper.getTngou().get(0).getName());

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void HideErrorView() {

    }


}

package com.adam.food.view.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.adam.food.R;
import com.adam.food.base.BaseActivity;
import com.adam.food.domain.TgClassifyWrapper;
import com.adam.food.presenter.main.ClassifyPresenter;
import com.adam.food.utils.SnackBarUtils;

import adamin.toolkits.utils.DialogUtil;
import adamin.toolkits.utils.LogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,MainView{
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private ClassifyPresenter presenter;
    AlertDialog alertDialog;

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
        alertDialog= DialogUtil.buildCustomDialog(MainActivity.this,"加载中");
        presenter=new ClassifyPresenter(this);
        presenter.getClassify("food",0);


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
        SnackBarUtils.showSnackBar(MainActivity.this,"出错了"+throwable.getMessage(),SnackBarUtils.ERROR);

    }

    @Override
    public void setData(TgClassifyWrapper tgClassifyWrapper) {
        LogUtil.error(MainActivity.class,tgClassifyWrapper.getTngou().get(0).getName());

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void HideErrorView() {

    }


}

package com.adam.food.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.adam.food.domain.TgClassify;
import com.adam.food.view.main.MainFragment;

import java.util.List;

/**
 * Created by adamlee on 2016/3/14.
 */
public class MainPagerAdapter  extends FragmentPagerAdapter {
    private List<TgClassify>  tgClassifies;
    private String apiname;

    public MainPagerAdapter(FragmentManager fm, List<TgClassify> tgClassifies,String apiname) {
        super(fm);
        this.apiname=apiname;
        this.tgClassifies = tgClassifies;
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance(apiname,tgClassifies.get(position).getId());
    }

    @Override
    public int getCount() {
        return tgClassifies.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tgClassifies.get(position).getName();
    }
}

package com.adam.food.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.adam.food.R;
import com.adam.food.adapter.RecyclerViewAdapter;
import com.adam.food.domain.TgClassify;
import com.adam.food.domain.TgClassifyWrapper;
import com.adam.food.presenter.main.ClassifyPresenter;
import com.adam.food.utils.SnackBarUtils;
import com.adamin.superrecyclerview.animator.adapters.SlideInLeftAnimationAdapter;
import com.adamin.superrecyclerview.superrecycer.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import adamin.toolkits.utils.DialogUtil;
import adamin.toolkits.utils.LogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by adamlee on 2016/3/14.
 */
public class MainFragment  extends Fragment  implements MainView {
    private String name="";
    private List<TgClassify> tgClassifies;
    private int id;
    private ClassifyPresenter classifyPresenter;
    private AlertDialog alertDialog;
    private RecyclerViewAdapter recyclerViewAdapter;
    @Bind(R.id.recyclerview)
    SuperRecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;


    public static  Fragment newInstance(String name,int id){
        MainFragment mainFragment=new MainFragment();
        Bundle bundle=new Bundle();
        bundle.putString("name",name);
        bundle.putInt("id",id);
        mainFragment.setArguments(bundle);
        return  mainFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.layout_list,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        name=getArguments().getString("name");
        id=getArguments().getInt("id");
        init();
        super.onViewCreated(view, savedInstanceState);
    }

    private void init() {
        gridLayoutManager=new GridLayoutManager(getContext(),2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position==tgClassifies.size()?gridLayoutManager.getSpanCount():1;
            }
        });
        linearLayoutManager=new LinearLayoutManager(getActivity());
        classifyPresenter=new ClassifyPresenter(this);
        tgClassifies=new ArrayList<>();
        alertDialog= DialogUtil.buildCustomCancelDialog(getActivity(),"正在加载列表数据");
        recyclerViewAdapter=new RecyclerViewAdapter(tgClassifies,getContext());
        recyclerView.setLayoutManager(gridLayoutManager);
        SlideInLeftAnimationAdapter slideInLeftAnimationAdapter=new SlideInLeftAnimationAdapter(recyclerViewAdapter);
        recyclerView.setAdapter(slideInLeftAnimationAdapter);
        classifyPresenter.getClassify(name,id);
        recyclerView.setRefreshingColorResources(R.color.md_amber_500,R.color.md_blue_500,R.color.md_brown_500
        ,R.color.md_orange_500);
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tgClassifies.clear();
                recyclerViewAdapter.notifyDataSetChanged();
                classifyPresenter.getClassify(name,id);
            }
        });


    }

    @Override
    public void showLoading() {
        alertDialog.show();

    }

    @Override
    public void hideLoaidng() {
        if(alertDialog!=null){
            alertDialog.dismiss();
        }

    }

    @Override
    public void showError(Throwable throwable) {
        SnackBarUtils.showSnackBar(getActivity(),throwable.getMessage()+"",SnackBarUtils.ERROR);

    }

    @Override
    public void setData(TgClassifyWrapper tgClassifyWrapper, String name) {
        tgClassifies.addAll(tgClassifyWrapper.getTngou());
        recyclerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void HideErrorView() {

    }
}

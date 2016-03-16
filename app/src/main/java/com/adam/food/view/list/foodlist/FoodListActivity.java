package com.adam.food.view.list.foodlist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.adam.food.R;
import com.adam.food.adapter.FoodListAdapter;
import com.adam.food.base.BaseActivity;
import com.adam.food.domain.foodlist.TgFoodList;
import com.adam.food.domain.foodlist.TgFoodListWrapper;
import com.adam.food.presenter.list.FoodListPresenter;
import com.adam.food.utils.SnackBarUtils;
import com.adamin.superrecyclerview.animator.adapters.SlideInLeftAnimationAdapter;
import com.adamin.superrecyclerview.superrecycer.OnMoreListener;
import com.adamin.superrecyclerview.superrecycer.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import adamin.toolkits.utils.DialogUtil;
import adamin.toolkits.utils.LogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class FoodListActivity extends BaseActivity implements FoodListView {

    private FoodListPresenter presenter;
    private AlertDialog alertDialog;
    private List<TgFoodList> tgFoodLists;
    private GridLayoutManager gridLayoutManager;
    private FoodListAdapter adapter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.superrecyclerview)
    SuperRecyclerView superRecyclerView;

    private int pagecount = 0;
    private int pagenow = 1;
    private int rows = 10;
    private int id = 3;

    private boolean loading = true;
    private int visibleThreshold = 2;// The minimum amount of items to have below your current scroll position before loading more.
    private int previousTotal = 0; // The total number of items in the dataset after the last load


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("食品- " + getIntent().getStringExtra("name"));
        id = getIntent().getIntExtra("id", 3);
        presenter = new FoodListPresenter(this);
        alertDialog = DialogUtil.buildCustomDialog(FoodListActivity.this, "数据正在加载中~");
        tgFoodLists = new ArrayList<>();
        gridLayoutManager = new GridLayoutManager(FoodListActivity.this, 2);
        adapter = new FoodListAdapter(tgFoodLists, FoodListActivity.this);
        superRecyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == tgFoodLists.size() ? gridLayoutManager.getSpanCount() : 1;
            }
        });
        SlideInLeftAnimationAdapter slideInLeftAnimationAdapter = new SlideInLeftAnimationAdapter(adapter);
        superRecyclerView.setAdapter(slideInLeftAnimationAdapter);
        presenter.getFoodList(id, rows, pagenow);
        superRecyclerView.setRefreshingColorResources(R.color.md_amber_500, R.color.md_blue_400
                , R.color.md_cyan_500, R.color.md_orange_500);
        superRecyclerView.getMoreProgressView().setVisibility(View.GONE);
        superRecyclerView.getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
                int visibleItemCount = gridLayoutManager.getChildCount();
                int totalItemCount = gridLayoutManager.getItemCount();
                int firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    // End has been reached
                    if (pagenow < pagecount) {
                        LogUtil.error(FoodListActivity.class, "数目" + pagecount);
                        pagenow++;
                        superRecyclerView.getMoreProgressView().setVisibility(View.VISIBLE);
                        presenter.getFoodList(id, rows, pagenow);
                        loading = true;
                    } else {
                        SnackBarUtils.showSnackBar(FoodListActivity.this, "到底咯", SnackBarUtils.INFO);
                    }
                    // Do something

                }
            }
        });
        adapter.setOnItemClickListener(new FoodListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                SnackBarUtils.showSnackBar(FoodListActivity.this, tgFoodLists.get(position).getName() + "", SnackBarUtils.SUCCESS);
            }
        });

    }

    @Override
    public void showLoading() {
        alertDialog.show();

    }

    @Override
    public void hideLoading() {
        alertDialog.dismiss();

    }

    @Override
    public void showError(Throwable e) {
        SnackBarUtils.showSnackBar(FoodListActivity.this, e.getMessage() + "", SnackBarUtils.ERROR);

    }

    @Override
    public void setData(TgFoodListWrapper tgFoodListWrapper) {
        pagecount = tgFoodListWrapper.getTotal() / rows + 1;
        LogUtil.error(FoodListActivity.class, "此时数目" + pagecount + "总数");
        if (tgFoodListWrapper.getTngou().size() > 0) {
            tgFoodLists.addAll(tgFoodListWrapper.getTngou());
            adapter.notifyDataSetChanged();

        }
        superRecyclerView.getMoreProgressView().setVisibility(View.GONE);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.thoughtworks.circledemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.thoughtworks.circledemo.adapter.CircleFriendAdapter;
import com.thoughtworks.circledemo.bean.CircleDymincBean;
import com.thoughtworks.circledemo.widget.DivItemDecoration;

import java.util.List;

/**
 * =======================================================
 * project:CircleFriend
 * copyright：Copyright (C) 2017 SHANXI XIAN Technologies Ltd
 * author：xszhang
 * date：created by 2017/11/4 09:17
 * version：V1.0
 * descript：朋友圈首页
 * =======================================================
 */
public class CircleFriendActivity extends Activity implements CircleFriendAdapter.OnItemButtonClickListener {
    private SuperRecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private CircleFriendAdapter adapter;
    private final static int TYPE_PULL_REFRESH = 1;
    private final static int TYPE_PULL_REFRESHMORE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }

    private void init() {
        this.initModule();
        this.initData();
        this.addListener();
    }

    private void initModule() {
        this.recyclerView = (SuperRecyclerView) findViewById(R.id.recyclerView);
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.addItemDecoration(new DivItemDecoration(2, true));
        this.recyclerView.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
    }

    private void initData() {
        //实现自动下拉刷新功能
        this.recyclerView.getSwipeToRefresh().post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setRefreshing(true);//执行下拉刷新的动画
                refreshListener.onRefresh();//执行数据加载操作
            }
        });
        this.adapter = new CircleFriendAdapter(this, this);
        this.recyclerView.setAdapter(adapter);
    }

    private void addListener() {
        this.refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadData(TYPE_PULL_REFRESH);
                    }
                }, 2000);
            }
        };
        this.recyclerView.setRefreshListener(refreshListener);
        this.recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                } else {
                }

            }
        });
    }

    /**
     * 加载数据
     *
     * @param loadType
     */
    public void loadData(int loadType) {
        updateLoadData(loadType, null);
    }

    /**
     * 更新数据，区分是上拉还是下拉
     *
     * @param loadType
     * @param datas
     */
    public void updateLoadData(int loadType, List<CircleDymincBean> datas) {
        if (loadType == TYPE_PULL_REFRESH) { //下拉刷新
            recyclerView.setRefreshing(false);
            adapter.setDatas(datas);
        } else if (loadType == TYPE_PULL_REFRESHMORE) {//加载底部更多
            adapter.getDatas().addAll(datas);
        }
        adapter.notifyDataSetChanged();
        if (null != adapter.getDatas() && adapter.getDatas().size() > 0) {
            if (adapter.getDatas().size() < 100 + CircleFriendAdapter.HEADVIEW_SIZE) {
                recyclerView.setupMoreListener(new OnMoreListener() {
                    @Override
                    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadData(TYPE_PULL_REFRESHMORE);
                            }
                        }, 2000);

                    }
                }, 1);
            } else {
                recyclerView.removeMoreListener();
                recyclerView.hideMoreProgress();
            }
        } else {
            recyclerView.removeMoreListener();
            recyclerView.hideMoreProgress();
        }
    }

    @Override
    public void onDeleteItemButtonClick(int position, int commentId) {

    }

    @Override
    public void addFavort(int mCirclePosition) {

    }

    @Override
    public void deleteFavort(int mCirclePosition, String mFavorId) {

    }
}

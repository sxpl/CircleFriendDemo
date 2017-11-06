package com.thoughtworks.circledemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.thoughtworks.circledemo.R;
import com.thoughtworks.circledemo.adapter.CircleFriendAdapter;
import com.thoughtworks.circledemo.bean.CircleDynamicBean;
import com.thoughtworks.circledemo.bean.CommentConfig;
import com.thoughtworks.circledemo.bean.CommentsBean;
import com.thoughtworks.circledemo.bean.PraiseBean;
import com.thoughtworks.circledemo.keyboard.KeyboardUtility;
import com.thoughtworks.circledemo.utils.DataTest;
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
    private CommentConfig commentConfig;
    private LinearLayout editTextBody;
    private EditText editText;
    private Button commentSend;

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
        this.editTextBody = (LinearLayout) findViewById(R.id.editTextBodyLl);
        this.editText = (EditText) findViewById(R.id.comment_et);
        this.commentSend = (Button) findViewById(R.id.comment_send);
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
        // 触发listView的时候关闭
        this.recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (editTextBody.getVisibility() == View.VISIBLE) {
                    updateEditTextBodyVisible(View.GONE, null);
                    return true;
                }
                return false;
            }
        });
        // 发布评论
        this.commentSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(CircleFriendActivity.this, "评论内容不能为空...", Toast.LENGTH_SHORT).show();
                    return;
                }
                CommentsBean commentsBean = null;
                if (commentConfig.commentType == CommentConfig.Type.PUBLIC) { //自己发表评论
                    commentsBean = DataTest.createPublicComment(content);
                } else if (commentConfig.commentType == CommentConfig.Type.REPLY) {//回复别人的评论
                }
                updateAddComment(commentConfig.circlePosition, commentsBean);
                updateEditTextBodyVisible(View.GONE, null);
            }
        });
    }

    /**
     * 加载数据
     *
     * @param loadType
     */
    public void loadData(int loadType) {
        List<CircleDynamicBean> list = DataTest.getCircleDyList();
        updateLoadData(loadType, list);
    }

    /**
     * 更新数据，区分是上拉还是下拉
     *
     * @param loadType
     * @param datas
     */
    public void updateLoadData(int loadType, List<CircleDynamicBean> datas) {
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
    public void onItemButtonClick(CommentConfig config) {
        commentConfig = config;
        if (commentConfig.commentType == CommentConfig.Type.REPLY) {
            editText.setHint("回复" + commentConfig.replyUser.getUsername() + ":");
        }
        this.updateEditTextBodyVisible(View.VISIBLE, commentConfig);
    }

    @Override
    public void onDeleteItemButtonClick(int position, int commentId) {

    }

    @Override
    public void addPraise(int mCirclePosition) {
        PraiseBean praiseBean = DataTest.createCurUserPraiseItem();
        updateAddPraise(mCirclePosition, praiseBean);
    }

    /**
     * 设置软键盘的显示隐藏
     *
     * @param visibility
     */
    public void updateEditTextBodyVisible(int visibility, CommentConfig config) {
        this.commentConfig = config;
        this.editTextBody.setVisibility(visibility);
        if (View.VISIBLE == visibility) {
            editText.requestFocus();
            //弹出键盘
            KeyboardUtility.showSoftInput(editText.getContext(), editText);
        } else if (View.GONE == visibility) {
            //隐藏键盘
            KeyboardUtility.hideSoftInput(editText.getContext(), editText);
        }
    }

    /**
     * 取消赞
     *
     * @param mCirclePosition
     * @param mFavorId
     */
    @Override
    public void deletePraise(int mCirclePosition, String mFavorId) {
        CircleDynamicBean item = adapter.getDatas().get(mCirclePosition);
        List<PraiseBean> items = item.getPraiseList();
        for (int i = 0; i < items.size(); i++) {
            if (mFavorId.equals(items.get(i).getId())) {
                items.remove(i);
                adapter.notifyDataSetChanged();
                return;
            }
        }
    }

    /**
     * 添加赞
     *
     * @param circlePosition
     * @param addItem
     */
    public void updateAddPraise(int circlePosition, PraiseBean addItem) {
        if (addItem != null) {
            CircleDynamicBean item = adapter.getDatas().get(circlePosition);
            item.getPraiseList().add(addItem);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (editTextBody != null && editTextBody.getVisibility() == View.VISIBLE) {
                updateEditTextBodyVisible(View.GONE, null);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 添加评论
     *
     * @param circlePosition
     * @param addItem
     */
    public void updateAddComment(int circlePosition, CommentsBean addItem) {
        if (addItem != null) {
            CircleDynamicBean item = adapter.getDatas().get(circlePosition);
            item.getComments().add(addItem);
            adapter.notifyDataSetChanged();
        }
        //清空评论文本
        this.editText.setText("");
    }
}

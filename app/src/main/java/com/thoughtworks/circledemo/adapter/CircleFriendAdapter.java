package com.thoughtworks.circledemo.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.thoughtworks.circledemo.R;
import com.thoughtworks.circledemo.bean.CircleDymincBean;

import java.util.List;

/**
 * =======================================================
 * project:CircleFriend
 * copyright：Copyright (C) 2017 SHANXI XIAN Technologies Ltd
 * author：xszhang
 * date：created by 2017/11/4 12:44
 * version：V1.0
 * descript：朋友圈适配器
 * =======================================================
 */

public class CircleFriendAdapter extends RecyclerView.Adapter<CircleFriendAdapter.CircleViewHolder> {
    public final static int TYPE_HEAD = 0;
    public final static int TYPE_CONTENT = 1;
    public static final int HEADVIEW_SIZE = 1;
    private Context mContext;
    private OnItemButtonClickListener mListener = null;//单击事件监听器
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    private List<CircleDymincBean> datas;

    public CircleFriendAdapter(Context context, OnItemButtonClickListener listener) {
        this.mContext = context;
        this.options = new DisplayImageOptions.Builder().cacheInMemory()
                .cacheOnDisc().showImageOnFail(R.drawable.default_img)
                .showStubImage(R.drawable.default_img)
                .showImageForEmptyUri(R.drawable.default_img).build();

        this.mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        } else {
            return TYPE_CONTENT;
        }
    }

    public List<CircleDymincBean> getDatas() {
        return datas;
    }

    public void setDatas(List<CircleDymincBean> datas) {
        this.datas = datas;

    }

    @Override
    public CircleFriendAdapter.CircleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CircleFriendAdapter.CircleViewHolder viewHolder = null;
        if (viewType == TYPE_HEAD) {
            View headView = LayoutInflater.from(mContext).inflate(R.layout.head_circle_layout, parent, false);
            viewHolder = new HeaderViewHolder(headView);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.circle_friend_item_layout, parent, false);
            viewHolder = new CircleViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CircleFriendAdapter.CircleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        // 有head需要加1
        return 10; //此处先写死，后面需要替换为动态数据
    }

    public class HeaderViewHolder extends CircleFriendAdapter.CircleViewHolder {
        ImageView imageViewHead;
        ImageView imageViewBg;
        TextView tvNickName;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            this.imageViewHead = (ImageView) itemView.findViewById(R.id.imageViewHead);
            this.imageViewBg = (ImageView) itemView.findViewById(R.id.imageViewBg);
            this.tvNickName = (TextView) itemView.findViewById(R.id.tvNickName);
        }
    }

    class CircleViewHolder extends RecyclerView.ViewHolder {

        public CircleViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 对外开放接口
     */
    public interface OnItemButtonClickListener {
//        void onItemButtonClick(CommentConfig config);

        void onDeleteItemButtonClick(int position, int commentId);

        void addFavort(int mCirclePosition);

        void deleteFavort(int mCirclePosition, String mFavorId);
    }
}


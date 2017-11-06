package com.thoughtworks.circledemo.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.thoughtworks.circledemo.R;
import com.thoughtworks.circledemo.bean.CircleDynamicBean;
import com.thoughtworks.circledemo.bean.CommentsBean;
import com.thoughtworks.circledemo.bean.ImageBean;
import com.thoughtworks.circledemo.bean.PraiseBean;
import com.thoughtworks.circledemo.utils.DataTest;
import com.thoughtworks.circledemo.utils.DateUtils;
import com.thoughtworks.circledemo.utils.Emoji;
import com.thoughtworks.circledemo.utils.EmojiUtils;
import com.thoughtworks.circledemo.utils.StringUtils;
import com.thoughtworks.circledemo.widget.CommentListView;
import com.thoughtworks.circledemo.widget.NineGridLayoutView;
import com.thoughtworks.circledemo.widget.PraiseListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.thoughtworks.circledemo.R.id.commentList;

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
    private List<CircleDynamicBean> datas;
    private int commonPosition;
    private String deleteItems[] = {"删除评论"};

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

    public List<CircleDynamicBean> getDatas() {
        return datas;
    }

    public void setDatas(List<CircleDynamicBean> datas) {
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
        if (getItemViewType(position) == TYPE_HEAD) {
            //  此处可处理头部数据
        } else {
            final int circlePosition = position - HEADVIEW_SIZE;
            commonPosition = circlePosition;
            final CircleDynamicBean campusNes = datas.get(circlePosition);
            holder.setData(circlePosition, campusNes);
        }
    }

    @Override
    public int getItemCount() {
        // 有head需要加1
        if (null != datas && datas.size() > 0) {
            return datas.size() + 1;
        }
        return 0;
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
        ImageView userPicture;    // 发送者头像小图地址
        TextView usernameTxt;     // 用户名称
        TextView content;         // 内容
        TextView time;            // 时间
        LinearLayout likesLayout;
        TextView likes;           // 点赞
        ImageView likesPicture;   // 点赞图标
        ImageView albumComment;   // 评论图标
        ImageView parise_icon;     // 赞图标
        TextView comments;        // 评论总数
        TextView deleteTv;
        NineGridLayoutView nineGridLayoutView;
        LinearLayout praiseListLayout; //点赞名单layout
        LinearLayout linearlayoutComment; //评论layout
        LinearLayout linearlayoutAll; //评论和赞总layout
        CommentListView commentListView; //评论列表
        PraiseListView praiseListView;//点赞列表
        View line;

        public CircleViewHolder(View convertView) {
            super(convertView);
            this.userPicture = (ImageView) convertView.findViewById(R.id.album_picture);
            this.usernameTxt = (TextView) convertView.findViewById(R.id.album_user_name_txt);
            this.content = (TextView) convertView.findViewById(R.id.album_content_txt);
            this.time = (TextView) convertView.findViewById(R.id.album_time);
            this.likesLayout = (LinearLayout) convertView.findViewById(R.id.album_praise_layout);
            this.likes = (TextView) convertView.findViewById(R.id.album_praise_number);
            this.likesPicture = (ImageView) convertView.findViewById(R.id.album_praise);
            this.albumComment = (ImageView) convertView.findViewById(R.id.album_comment);
            this.parise_icon = (ImageView) convertView.findViewById(R.id.parise_icon);
            this.comments = (TextView) convertView.findViewById(R.id.album_comment_number);
            this.deleteTv = (TextView) convertView.findViewById(R.id.tv_delete);
            this.nineGridLayoutView = (NineGridLayoutView) convertView.findViewById(R.id.layout_nine_grid);
            this.praiseListLayout = (LinearLayout) convertView.findViewById(R.id.album_praise_list_layout);
            this.linearlayoutComment = (LinearLayout) convertView.findViewById(R.id.linearlayout_comment);
            this.linearlayoutAll = (LinearLayout) convertView.findViewById(R.id.linearlayoutAll);
            this.commentListView = (CommentListView) convertView.findViewById(commentList);
            this.praiseListView = (PraiseListView) convertView.findViewById(R.id.praiseListView);
            this.line = convertView.findViewById(R.id.line);
        }

        /**
         * 设置数据
         *
         * @param circlePosition
         * @param circleDynamicBean
         */
        public void setData(final int circlePosition, final CircleDynamicBean circleDynamicBean) {
            Log.i("[app]", "circleDynamicBean=" + circleDynamicBean);
            this.userPicture.setTag(circlePosition + "");
            imageLoader.displayImage(circleDynamicBean.getSender().getAvatar(), this.userPicture, options);
            this.usernameTxt.setText(circleDynamicBean.getSender().getUsername());
            String dateSource = circleDynamicBean.getDt();
            this.time.setText(DateUtils.getModularizationDate(Long.parseLong(dateSource)));
            // 判断接受到的是否有表情图片，有则替换
            if (!StringUtils.isEmpty(circleDynamicBean.getContent())) {
                for (int i = 0; i < EmojiUtils.picStr.length; i++) {
                    if (circleDynamicBean.getContent().contains("[" + EmojiUtils.picStr[i] + "]")) {
                        String s1 = circleDynamicBean.getContent();
                        String s = s1.replaceAll("\\[" + EmojiUtils.picStr[i] + "\\]", "<f" + EmojiUtils.picStr1[i] + ">");
                        circleDynamicBean.setContent(s);
                    }
                }
            }
            if (circleDynamicBean.getContent() != null && circleDynamicBean.getContent().contains("<f") && circleDynamicBean.getContent().contains(">")) {
                this.content.setText("");
                String message = circleDynamicBean.getContent();
                List<Object> results = new ArrayList<Object>();
                List<String> ems = new ArrayList<String>();
                Pattern patter = Pattern.compile("<f[\\w]*>");
                Matcher matcher = patter.matcher(circleDynamicBean.getContent());
                while (matcher.find()) {
                    ems.add(matcher.group());
                }
                for (int i = 0; i < ems.size(); i++) {
                    if (message.startsWith("<f")) {
                        results.add(message.substring(0, 6));
                        message = message.substring(6, message.length());
                        if (message.length() > 0 && !message.startsWith("<f")) {
                            if (message.contains("<f") && message.contains(">")) {
                                int emsIndex = message.indexOf("<");
                                String itemMes = message.substring(0, emsIndex);
                                results.add(itemMes);
                                message = message.substring(emsIndex, message.length());
                            } else {
                                results.add(message);
                            }
                        }
                    } else {
                        int emsIndex = message.indexOf("<");
                        String itemMes = message.substring(0, emsIndex);
                        results.add(itemMes);
                        message = message.substring(emsIndex, message.length());
                        results.add(message.substring(0, 6));
                        message = message.substring(6, message.length());
                    }
                }
                ArrayList<SpannableString> list = new ArrayList<SpannableString>();
                for (int i = 0; i < results.size(); i++) {
                    list.add(null);
                }
                for (int i = 0; i < results.size(); i++) {
                    if (results.get(i).toString().startsWith("<f")) {
                        String emPath = results.get(i).toString().replace("<", "");
                        emPath = emPath.replace(">", "");
                        emPath = emPath.substring(1, 4);
                        list.set(i, Emoji.getImg(mContext, emPath));
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != null) {
                        results.set(i, list.get(i));
                    }
                }
                for (int i = 0; i < results.size(); i++) {
                    this.content.append((CharSequence) results.get(i));
                }
            } else {
                this.content.setText(circleDynamicBean.getContent());
            }
            // 处理图片
            Collection<ImageBean> imageList = circleDynamicBean.getImages();
            if (imageList != null && imageList.size() > 0) { //图片展示
                Iterator iterator = imageList.iterator();
                int index = 0;
                int length;

                length = imageList.size();
                final String urls[] = new String[length];
                while (iterator.hasNext()) {
                    ImageBean image = (ImageBean) iterator.next();
                    urls[index] = image.getUrl();
                    index++;
                }
                List<String> urlLists = new ArrayList<String>();
                Collections.addAll(urlLists, urls);
                this.nineGridLayoutView.setVisibility(View.VISIBLE);
                this.nineGridLayoutView.setUrlList(urlLists);
            } else {
                this.nineGridLayoutView.setVisibility(View.GONE);
            }
            // 处理点赞列表
            final List<PraiseBean> praiseBeanList = circleDynamicBean.getPraiseList();
            // 处理评论列表
            final List<CommentsBean> commentsBeanList = circleDynamicBean.getComments();
            boolean hasPraise = circleDynamicBean.hasPraise();
            boolean hasComment = circleDynamicBean.hasComment();
            if (hasPraise || hasComment) {
                this.linearlayoutAll.setVisibility(View.VISIBLE);
                //处理点赞列表
                if (hasPraise) {
                    this.line.setVisibility(View.VISIBLE);
                    this.praiseListLayout.setVisibility(View.VISIBLE);
                    this.praiseListView.setVisibility(View.VISIBLE);
                    this.parise_icon.setVisibility(View.VISIBLE);
                    this.praiseListView.setDatas(praiseBeanList);
                    this.praiseListView.setOnItemClickListener(new PraiseListView.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            String userName = praiseBeanList.get(position).getSenderBean().getUsername();
                            String userId = praiseBeanList.get(position).getSenderBean().getId();
                            Toast.makeText(mContext, userName + " &id = " + userId, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    this.praiseListLayout.setVisibility(View.GONE);
                    this.line.setVisibility(View.GONE);
                    this.praiseListView.setVisibility(View.GONE);
                    this.parise_icon.setVisibility(View.GONE);
                }
                // 处理评论列表
                if (hasComment) {
                    this.commentListView.setVisibility(View.VISIBLE);
                    this.commentListView.setDatas(commentsBeanList);
                    // 处理评论列表点击事件
                    this.commentListView.setOnItemClickListener(new CommentListView.OnItemClickListener() {
                        @Override
                        public void onItemClick(int commentPosition) {
                            final CommentsBean commentsBean = commentsBeanList.get(commentPosition);
                            if (DataTest.curUser.getId().equals(commentsBean.getSender().getId())) {//删除自己的评论
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setItems(deleteItems, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        mListener.onDeleteItemButtonClick(circlePosition, commentsBean.getCommentId());
                                    }
                                });
                                builder.create().show();
                            } else { //回复别人的评论
//                            CommentConfig config = new CommentConfig();
//                            config.circlePosition = circlePosition;
//                            config.commentPosition = commentPosition;
//                            config.commentType = CommentConfig.Type.REPLY;
//                            config.replyUser = campusNewsComment.getUser();
//                            mListener.onItemButtonClick(config);
                            }

                        }
                    });
                } else {
                    this.commentListView.setVisibility(View.GONE);
                    this.linearlayoutAll.setVisibility(View.GONE);
                }
            } else {
                this.linearlayoutAll.setVisibility(View.GONE);
            }
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


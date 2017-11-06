package com.thoughtworks.circledemo.bean;


import android.text.TextUtils;

import java.io.Serializable;
import java.util.List;

/**
 * =======================================================
 * project:CircleFriend
 * copyright：Copyright (C) 2017 SHANXI XIAN Technologies Ltd
 * author：xszhang
 * date：created by 2017/11/4 14:15
 * version：V1.0
 * descript：班级动态bean
 * =======================================================
 */
public class CircleDynamicBean implements Serializable {
    private int id;// 动态ID
    private String dt;// 动态消息发布时间
    private int type;// 动态内容，1为普通内容，2为链接内容，3为系统内容
    private String content;// 动态内容
    private SenderBean sender;//发送者用户信息
    private List<CommentsBean> comments;//评论信息
    private List<ImageBean> images; // 图片
    private List<PraiseBean> praiseList;//点赞列表

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SenderBean getSender() {
        return sender;
    }

    public void setSender(SenderBean sender) {
        this.sender = sender;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public List<ImageBean> getImages() {
        return images;
    }

    public void setImages(List<ImageBean> images) {
        this.images = images;
    }

    public List<PraiseBean> getPraiseList() {
        return praiseList;
    }

    public void setPraiseList(List<PraiseBean> praiseList) {
        this.praiseList = praiseList;
    }

    /**
     * 是否点赞
     *
     * @return
     */
    public boolean hasPraise() {
        if (praiseList != null && praiseList.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 是否评论
     *
     * @return
     */
    public boolean hasComment() {
        if (comments != null && comments.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取当前用户的点赞id
     *
     * @param curUserId
     * @return
     */
    public String getCurUserPraiseId(String curUserId) {
        String praiseId = "";
        if (!TextUtils.isEmpty(curUserId) && hasPraise()) {
            for (PraiseBean item : praiseList) {
                if (curUserId.equals(item.getSenderBean().getId())) {
                    praiseId = item.getId();
                    return praiseId;
                }
            }
        }
        return praiseId;
    }
}

package com.thoughtworks.circledemo.bean;

import java.io.Serializable;

/**
 * =======================================================
 * project:CircleFriend
 * copyright：Copyright (C) 2017 SHANXI XIAN Technologies Ltd
 * author：xszhang
 * date：created by 2017/11/6 14:22
 * version：V1.0
 * descript：回复功能
 * =======================================================
 */
public class CommentConfig implements Serializable {
    public enum Type {
        PUBLIC("public"), REPLY("reply");
        private String value;

        Type(String value) {
            this.value = value;
        }
    }

    public int circlePosition; //班级圈位置
    public int commentPosition; //评论位置
    public SenderBean replyUser;
    public Type commentType;

    @Override
    public String toString() {
        String replyUserStr = "";
        if (replyUser != null) {
            replyUserStr = replyUser.toString();
        }
        return "circlePosition = " + circlePosition
                + "; commentPosition = " + commentPosition
                + "; commentType ＝ " + commentType
                + "; replyUser = " + replyUserStr;
    }
}

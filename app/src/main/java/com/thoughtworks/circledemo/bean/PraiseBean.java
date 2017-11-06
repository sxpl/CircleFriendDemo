package com.thoughtworks.circledemo.bean;

import java.io.Serializable;


/**
 * =======================================================
 * project:CircleFriend
 * copyright：Copyright (C) 2017 SHANXI XIAN Technologies Ltd
 * author：xszhang
 * date：created by 2017/11/6 13:55
 * version：V1.0
 * descript：点赞实体
 * =======================================================
 */
public class PraiseBean implements Serializable {
    private String id;
    private SenderBean senderBean;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SenderBean getSenderBean() {
        return senderBean;
    }

    public void setSenderBean(SenderBean senderBean) {
        this.senderBean = senderBean;
    }
}

package com.thoughtworks.circledemo.bean;

import java.io.Serializable;

/**
 * =======================================================
 * project:CircleFriend
 * copyright：Copyright (C) 2017 SHANXI XIAN Technologies Ltd
 * author：xszhang
 * date：created by 2017/11/5 20:55
 * version：V1.0
 * descript：动态图片bean
 * =======================================================
 */
public class ImageBean implements Serializable {
    private String url;//图片url

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package com.thoughtworks.circledemo.listener;


/**
 * =======================================================
 * project:CircleFriend
 * copyright：Copyright (C) 2017 SHANXI XIAN Technologies Ltd
 * author：xszhang
 * date：created by 2017/11/4 09:28
 * version：V1.0
 * descript：对外接口
 * =======================================================
 */
public interface RecycleViewItemListener {

    void onItemClick(int position);

    boolean onItemLongClick(int position);
}

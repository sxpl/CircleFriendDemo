package com.thoughtworks.circledemo.bean;

import java.io.Serializable;


/**
 * =======================================================
 * project:CircleFriend
 * copyright：Copyright (C) 2017 SHANXI XIAN Technologies Ltd
 * author：xszhang
 * date：created by 2017/11/5 20:47
 * version：V1.0
 * descript：发送者实体
 * =======================================================
 */
public class SenderBean implements Serializable {
    private String id;
    private String username;//用户姓名
    private String nick;//用户昵称
    private String avatar;//用户头像

    public SenderBean(String id, String username, String nick, String avatar) {
        this.id = id;
        this.username = username;
        this.nick = nick;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

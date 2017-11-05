package com.thoughtworks.circledemo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * =======================================================
 * project:CircleFriend
 * copyright：Copyright (C) 2017 SHANXI XIAN Technologies Ltd
 * author：xszhang
 * date：created by 2017/11/5 21:36
 * version：V1.0
 * descript：日期时间戳工具
 * =======================================================
 */
public class DateUtils {
    /**
     * 获取动态格式时间
     *
     * @param milliseconds
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("deprecation")
    public static String getModularizationDate(long milliseconds) {
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fm3 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat fm4 = new SimpleDateFormat("MM-dd HH:mm");
        if (milliseconds < 0) return "时间异常";
        Date history = new Date(milliseconds);
        Date current = new Date();
        // 判断年
        if (current.getYear() != history.getYear()) {
            return fm1.format(history);
        }
        // 判断月
        if (current.getMonth() != history.getMonth()) {
            return fm1.format(history);
        }
        // 判断日
        int intervalDay = current.getDate() - history.getDate();
        // 超过2天
        if (intervalDay > 2) {
            return fm1.format(history);
        } else if (intervalDay > 1) {
            return "前天";
        } else if (intervalDay > 0) {
            return "昨天 " + fm3.format(history);
        } else if (intervalDay < 0) { //时间超前1天及以上，一个月以内用fm4
            return fm4.format(history);
        }
        //今天
        int intervalHour = current.getHours() - history.getHours(); // 获取间隔了多少小时
        int intervalMinute = (int) ((current.getTime() - history.getTime()) / (60 * 1000)); // 获取间隔了多少分钟
        // 时间超前
        if (intervalMinute < 0) {
            return "今天 " + fm3.format(history);
        }
        //刚刚
        if (intervalMinute < 3) {
            return "刚刚";
        }
        if (intervalMinute < 60) {
            return intervalMinute + "分钟前";
        } else {
            return intervalHour + "小时前";
        }
    }
}

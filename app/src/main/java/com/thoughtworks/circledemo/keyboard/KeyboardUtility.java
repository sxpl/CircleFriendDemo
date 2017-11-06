/**
 * Copyright (C) 2014 Guangzhou QTONE Technologies Ltd.
 * <p>
 * 本代码版权归广州全通教育股份有限公司所有，且受到相关的法律保护。没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 * @date 2014年10月15日 下午6:06:28
 * @version V1.0
 */
package com.thoughtworks.circledemo.keyboard;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;


/**
 * =======================================================
 * project:CircleFriend
 * copyright：Copyright (C) 2017 SHANXI XIAN Technologies Ltd
 * author：xszhang
 * date：created by 2017/11/6 15:07
 * version：V1.0
 * descript：软键盘
 * =======================================================
 */
public class KeyboardUtility {

    /**
     * Hide keyboard
     *
     * @param activity
     */
    public static void closeKeyboard(Activity activity) {

        try {

            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive() && null != activity.getCurrentFocus() && null != activity.getCurrentFocus().getWindowToken()) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 展示软键盘
    public static void showkeyboard(final EditText edit, final Activity activity) {
        try {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        InputMethodManager m = (InputMethodManager) edit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        m.showSoftInputFromInputMethod(activity.getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_FORCED);
                        m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 软键盘开关
     *
     * @param context
     * @author hexiaodong
     */
    public static void toggleSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 得到InputMethodManager的实例
        if (imm.isActive()) {
            // 如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
            // 关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        }
    }

    public static void showSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        //imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public static void hideSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }

    public static boolean isShowSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        //获取状态信息
        return imm.isActive();//true 打开
    }
}

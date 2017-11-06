package cn.qtone.xxt.spannable;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import cn.qtone.xxt.ui.BaseApplication;
import gdalbum.cn.qtone.xxt.R;

/**
 * =======================================================
 * 项目名称:广东和教育APP
 * 版权：Copyright (C) 2014 GuangDong QTONE Technologies Ltd
 * 作者：xszhang
 * 日期：created by 2017/9/7 17:49
 * 版本：V1.0
 * 描述：
 * =======================================================
 */
public abstract class SpannableClickable extends ClickableSpan implements View.OnClickListener {

    private int DEFAULT_COLOR_ID = R.color.color_8290AF;
    /**
     * text颜色
     */
    private int textColor;

    public SpannableClickable() {
        this.textColor = BaseApplication.getAppContext().getResources().getColor(DEFAULT_COLOR_ID);
    }

    public SpannableClickable(int textColor) {
        this.textColor = textColor;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(textColor);
        ds.setUnderlineText(false);
        ds.clearShadowLayer();
    }
}

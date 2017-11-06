package cn.qtone.xxt.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Browser;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.qtone.xxt.spannable.SpannableClickable;


/**
 * =======================================================
 * 项目名称:广东和教育APP
 * 版权：Copyright (C) 2014 GuangDong QTONE Technologies Ltd
 * 作者：xszhang
 * 日期：created by 2017/9/8 09:56
 * 版本：V1.0
 * 描述：处理链接相关url
 * =======================================================
 */
public class UrlUtils {

    public static SpannableStringBuilder formatUrlString(String contentStr) {

        SpannableStringBuilder sp;
        if (!TextUtils.isEmpty(contentStr)) {

            sp = new SpannableStringBuilder(contentStr);
            try {
                //处理url匹配
                Pattern urlPattern = Pattern.compile("(http|https|ftp|svn)://([a-zA-Z0-9]+[/?.?])" +
                        "+[a-zA-Z0-9]*\\??([a-zA-Z0-9]*=[a-zA-Z0-9]*&?)*");
                Matcher urlMatcher = urlPattern.matcher(contentStr);

                while (urlMatcher.find()) {
                    final String url = urlMatcher.group();
                    if (!TextUtils.isEmpty(url)) {
                        sp.setSpan(new SpannableClickable() {
                            @Override
                            public void onClick(View widget) {
                                Uri uri = Uri.parse(url);
                                Context context = widget.getContext();
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                intent.putExtra(Browser.EXTRA_APPLICATION_ID, context.getPackageName());
                                context.startActivity(intent);
                            }
                        }, urlMatcher.start(), urlMatcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

                //处理电话匹配
                Pattern phonePattern = Pattern.compile("[1][34578][0-9]{9}");
                Matcher phoneMatcher = phonePattern.matcher(contentStr);
                while (phoneMatcher.find()) {
                    final String phone = phoneMatcher.group();
                    if (!TextUtils.isEmpty(phone)) {
                        sp.setSpan(new SpannableClickable() {
                            @Override
                            public void onClick(View widget) {
                                Context context = widget.getContext();
                                //用intent启动拨打电话
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }
                        }, phoneMatcher.start(), phoneMatcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            sp = new SpannableStringBuilder();
        }
        return sp;
    }
}

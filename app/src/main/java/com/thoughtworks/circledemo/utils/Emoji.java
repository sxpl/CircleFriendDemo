package com.thoughtworks.circledemo.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.thoughtworks.circledemo.R;


public class Emoji {
    //f080-f089为签到表情
    public static int[] mStrs = new int[]{R.drawable.f000, R.drawable.f001,
            R.drawable.f002, R.drawable.f003, R.drawable.f004, R.drawable.f005,
            R.drawable.f006, R.drawable.f007, R.drawable.f008, R.drawable.f009,
            R.drawable.f010, R.drawable.f011, R.drawable.f012, R.drawable.f013,
            R.drawable.f014, R.drawable.f015, R.drawable.f016, R.drawable.f017,
            R.drawable.f018, R.drawable.f019, R.drawable.delate,
            R.drawable.f020, R.drawable.f021, R.drawable.f022, R.drawable.f023,
            R.drawable.f024, R.drawable.f025, R.drawable.f026, R.drawable.f027,
            R.drawable.f028, R.drawable.f029, R.drawable.f030, R.drawable.f031,
            R.drawable.f032, R.drawable.f033, R.drawable.f034, R.drawable.f035,
            R.drawable.f036, R.drawable.f037, R.drawable.f038, R.drawable.f039,
            R.drawable.delate, R.drawable.f040, R.drawable.f041,
            R.drawable.f042, R.drawable.f043, R.drawable.f044, R.drawable.f045,
            R.drawable.f046, R.drawable.f047, R.drawable.f048, R.drawable.f049,
            R.drawable.f050, R.drawable.f051, R.drawable.f052, R.drawable.f053,
            R.drawable.f054, R.drawable.f055, R.drawable.f056, R.drawable.f057,
            R.drawable.f058, R.drawable.f059, R.drawable.delate,
            R.drawable.f060, R.drawable.f061, R.drawable.f062, R.drawable.f063,
            R.drawable.f064, R.drawable.f065, R.drawable.f066, R.drawable.f067,
            R.drawable.f068, R.drawable.f069, R.drawable.f070, R.drawable.f071,
            R.drawable.f072, R.drawable.f073, R.drawable.f074, R.drawable.f075,
            R.drawable.f076, R.drawable.f077, R.drawable.f078, R.drawable.f079,
            R.drawable.f080, R.drawable.f081, R.drawable.f082, R.drawable.f083,
            R.drawable.f084, R.drawable.f085, R.drawable.f086, R.drawable.f087,
            R.drawable.f088, R.drawable.f089, R.drawable.delate};

    public static int[] picture = new int[]{R.drawable.f000, R.drawable.f001,
            R.drawable.f002, R.drawable.f003, R.drawable.f004, R.drawable.f005,
            R.drawable.f006, R.drawable.f007, R.drawable.f008, R.drawable.f009,
            R.drawable.f010, R.drawable.f011, R.drawable.f012, R.drawable.f013,
            R.drawable.f014, R.drawable.f015, R.drawable.f016, R.drawable.f017,
            R.drawable.f018, R.drawable.f019, R.drawable.f020, R.drawable.f021,
            R.drawable.f022, R.drawable.f023, R.drawable.f024, R.drawable.f025,
            R.drawable.f026, R.drawable.f027, R.drawable.f028, R.drawable.f029,
            R.drawable.f030, R.drawable.f031, R.drawable.f032, R.drawable.f033,
            R.drawable.f034, R.drawable.f035, R.drawable.f036, R.drawable.f037,
            R.drawable.f038, R.drawable.f039, R.drawable.f040, R.drawable.f041,
            R.drawable.f042, R.drawable.f043, R.drawable.f044, R.drawable.f045,
            R.drawable.f046, R.drawable.f047, R.drawable.f048, R.drawable.f049,
            R.drawable.f050, R.drawable.f051, R.drawable.f052, R.drawable.f053,
            R.drawable.f054, R.drawable.f055, R.drawable.f056, R.drawable.f057,
            R.drawable.f058, R.drawable.f059, R.drawable.f060, R.drawable.f061,
            R.drawable.f062, R.drawable.f063, R.drawable.f064, R.drawable.f065,
            R.drawable.f066, R.drawable.f067, R.drawable.f068, R.drawable.f069,
            R.drawable.f070, R.drawable.f071, R.drawable.f072, R.drawable.f073,
            R.drawable.f074, R.drawable.f075, R.drawable.f076, R.drawable.f077,
            R.drawable.f078, R.drawable.f079, R.drawable.f080, R.drawable.f081,
            R.drawable.f082, R.drawable.f083, R.drawable.f084, R.drawable.f085,
            R.drawable.f086, R.drawable.f087, R.drawable.f088, R.drawable.f089};


    /**
     * 根据图片名获取ID,并生成图片对象
     *
     * @param faceName
     * @return ss
     */
    public static SpannableString getImg(Context context, String faceId) {
        SpannableString ss = new SpannableString("<f" + faceId + ">");
        // SpannableString ss = new SpannableString("[_nl_qqImg]" + faceId+
        // ".gif[/_nl_qqImg]");
        Drawable d = context.getResources().getDrawable(picture[Integer.parseInt(faceId)]);
        d.setBounds(0, 10, d.getIntrinsicWidth(), d.getIntrinsicHeight() + 10);
        ImageSpan imgSpan = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        ss.setSpan(imgSpan, 0, ss.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }


}

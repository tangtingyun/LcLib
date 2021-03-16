package com.step.lclib.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DisplayHelper {

    // 单位转换 dp -> px
    public static int dp2px(Context context, int dp) {
        return (int) (getDensity(context) * dp + 0.5);
    }

    // 单位转换  sp -> px
    public static int sp2px(Context context, int sp) {
        return (int) (getFontDensity(context) * sp + 0.5);
    }

    public static int getScreenWidth(Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static float getFontDensity(Context context) {
        return context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }


}

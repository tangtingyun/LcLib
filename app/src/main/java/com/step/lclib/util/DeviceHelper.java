package com.step.lclib.util;

import android.os.Build;

public class DeviceHelper {
    public static boolean useFeature() {
        return Build.VERSION.SDK_INT >= 21;
    }
}

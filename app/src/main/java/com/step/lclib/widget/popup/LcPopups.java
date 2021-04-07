package com.step.lclib.widget.popup;

import android.content.Context;
import android.view.ViewGroup;

public class LcPopups {

    public static LcPopup popup(Context context) {
        return new LcPopup(context,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public static LcPopup popup(Context context, int width) {
        return new LcPopup(context,
                width,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public static LcPopup popup(Context context, int width, int height) {
        return new LcPopup(context, width, height);
    }
}

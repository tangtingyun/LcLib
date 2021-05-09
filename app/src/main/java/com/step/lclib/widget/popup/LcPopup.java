package com.step.lclib.widget.popup;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

public class LcPopup extends LcNormalPopup<LcPopup> {

    public LcPopup(Context context, int width, int height) {
        super(context, width, height);
    }

    @Override
    public LcPopup show(@NonNull View anchor) {
        return super.show(anchor);
    }
}

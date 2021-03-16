package com.step.lclib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.step.lclib.util.LayoutHelper;

public class CornerLinearLayout extends LinearLayout {
    public CornerLinearLayout(Context context) {
        this(context, null);
    }

    public CornerLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornerLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CornerLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutHelper.setViewOutline(this, attrs, defStyleAttr, defStyleRes);
    }

    public void setViewOutline(int radius, int radiusSide) {
        LayoutHelper.setViewOutline(this, radius, radiusSide);
    }
}

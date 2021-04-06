package com.step.lclib.widget.roundwidget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.step.lclib.util.LayoutHelper;

/**
 * 代码来自 QMUI  自己做了调整
 * 使按钮能方便地指定圆角、边框颜色、边框粗细、背景色
 */
public class RoundButton extends AppCompatButton {

    RoundDrawable mRoundBg;

    public RoundButton(@NonNull Context context) {
        super(context);
        init(context, null, 0);
    }

    public RoundButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public RoundButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mRoundBg = RoundDrawable.fromAttributeSet(context, attrs, defStyleAttr);
        LayoutHelper.setBackgroundKeepingPadding(this, mRoundBg);
    }
}

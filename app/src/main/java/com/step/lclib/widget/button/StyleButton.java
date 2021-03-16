package com.step.lclib.widget.button;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;

import com.step.lclib.R;

public class StyleButton extends AppCompatButton {
    public StyleButton(Context context) {
        super(context);
    }

    public StyleButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        Class b = R.attr.class;  //一个属性  指向某一个具体的style   指向一个实现了的定义
        Class e = R.style.class;  // 具体的某一个style               实现了一个定义
        Class f = R.styleable.class; // 支持自定义属性的一个集合    是一个定义
    }

    public StyleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
/*
        context.obtainStyledAttributes(
                attrs,          // 开发者写在xml中的属性集合
                null,           // 你想要获取的属性集合定义
                defStyleAttr,   //
                defStyleRes     //
                );
        */
    }
}

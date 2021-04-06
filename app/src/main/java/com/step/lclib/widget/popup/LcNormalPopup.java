package com.step.lclib.widget.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LcNormalPopup<T extends LcBasePopup> extends LcBasePopup<T> {

    public static final int ANIM_AUTO = 0;
    public static final int ANIM_GROW_FROM_LEFT = 1;
    public static final int ANIM_GROW_FROM_RIGHT = 2;
    public static final int ANIM_GROW_FROM_CENTER = 3;
    public static final int ANIM_SPEC = 4;

    @IntDef(value = {ANIM_AUTO, ANIM_GROW_FROM_CENTER, ANIM_GROW_FROM_LEFT, ANIM_GROW_FROM_RIGHT, ANIM_SPEC})
    @interface AnimStyle {
    }

    public static final int DIRECTION_TOP = 0;
    public static final int DIRECTION_BOTTOM = 1;
    public static final int DIRECTION_CENTER_IN_SCREEN = 2;

    @IntDef({DIRECTION_BOTTOM, DIRECTION_TOP, DIRECTION_CENTER_IN_SCREEN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Direction {
    }

    protected @AnimStyle
    int mAnimStyle;
    protected int mSpecAnimStyle;
    private int mEdgeProtectionTop;
    private int mEdgeProtectionLeft;
    private int mEdgeProtectionRight;
    private int mEdgeProtectionBottom;

    private boolean mShowArrow = true;
    private boolean mAddShadow = false;
    private int mRadius = NOT_SET;
    private int mBorderColor = Color.TRANSPARENT;
    private int mBorderUsedColor = Color.TRANSPARENT;

    private int mBorderWidth = NOT_SET;
    private int mShadowElevation = NOT_SET;
    private float mShadowAlpha = 0f;
    private int mShadowInset = NOT_SET;
    private int mBgColor = Color.TRANSPARENT;
    private boolean mIsBgColorSet = false;
    private int mBgUsedColor = Color.TRANSPARENT;
    private int mOffsetX = 0;
    private int mOffsetYIfTop = 0;
    private int mOffsetIfBottom = 0;
    private @Direction
    int mPreferredDirection = DIRECTION_BOTTOM;
    protected final int mInitWidth;
    protected final int mInitHeight;
    private int mArrowWidth = NOT_SET;
    private int mArrowHeight = NOT_SET;
    private boolean mRemoveBorderWhenShadow = false;
    private View mContentView;

    public LcNormalPopup(Context context, int width, int height) {
        super(context);
        mInitWidth = width;
        mInitHeight = height;
    }

    public T arrow(boolean showArrow){
        mShowArrow = showArrow;
        return (T) this;
    }















}

package com.step.lclib.widget.roundwidget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;

import com.step.lclib.R;

public class RoundDrawable extends GradientDrawable {

    // 圆角大小是否自适应为View高度的一半
    private boolean mRadiusAdjustBounds = true;
    private ColorStateList mFillColors;
    private int mStrokeWidth = 0;
    private ColorStateList mStrokeColors;

    // 设置按钮的背景色 只支持纯色 不支持Bitmap 或 Drawable
    public void setBgData(ColorStateList colors) {
        super.setColor(colors);
    }

    // 设置按钮的描边粗细和颜色
    public void setStrokeData(int width, ColorStateList colors) {
        mStrokeWidth = width;
        mStrokeColors = colors;
        super.setStroke(width, colors);
    }

    public int getStrokeWidth() {
        return mStrokeWidth;
    }

    public void setStrokeColors(ColorStateList colors) {
        setStrokeData(mStrokeWidth, colors);
    }

    public void setIsRadiusAdjustBounds(boolean isRadiusAdjustBounds) {
        this.mRadiusAdjustBounds = isRadiusAdjustBounds;
    }

    @Override
    protected boolean onStateChange(int[] stateSet) {
        boolean superRet = super.onStateChange(stateSet);
        if (mFillColors != null) {
            int color = mFillColors.getColorForState(stateSet, 0);
            setColor(color);
            superRet = true;
        }

        if (mStrokeColors != null) {
            int color = mStrokeColors.getColorForState(stateSet, 0);
            setStroke(mStrokeWidth, color);
            superRet = true;
        }


        return superRet;
    }

    @Override
    public boolean isStateful() {
        return (mFillColors != null && mFillColors.isStateful())
                || (mStrokeColors != null && mStrokeColors.isStateful())
                || super.isStateful();
    }

    @Override
    protected void onBoundsChange(Rect r) {
        super.onBoundsChange(r);
        if (mRadiusAdjustBounds){
            setCornerRadius(Math.min(r.width(),r.height()/2));
        }
    }

    public static RoundDrawable fromAttributeSet(Context context,
                                                 AttributeSet attrs,
                                                 int defStyleAttr){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundWidget, defStyleAttr, R.style.LcButton_Round);
        ColorStateList colorBg = typedArray.getColorStateList(R.styleable.RoundWidget_round_backgroundColor);
        ColorStateList colorBorder = typedArray.getColorStateList(R.styleable.RoundWidget_round_borderColor);
        int borderWidth = typedArray.getDimensionPixelSize(R.styleable.RoundWidget_round_borderWidth, 0);
        boolean isRadiusAdjustBounds = typedArray.getBoolean(R.styleable.RoundWidget_round_isRadiusAdjustBounds, false);
        int mRadius = typedArray.getDimensionPixelSize(R.styleable.RoundWidget_round_radius, 0);
        int mRadiusTopLeft = typedArray.getDimensionPixelSize(R.styleable.RoundWidget_round_radiusTopLeft, 0);
        int mRadiusTopRight = typedArray.getDimensionPixelSize(R.styleable.RoundWidget_round_radiusTopRight, 0);
        int mRadiusBottomLeft = typedArray.getDimensionPixelSize(R.styleable.RoundWidget_round_radiusBottomLeft, 0);
        int mRadiusBottomRight = typedArray.getDimensionPixelSize(R.styleable.RoundWidget_round_radiusBottomRight, 0);
        typedArray.recycle();
        return null;
    }


}

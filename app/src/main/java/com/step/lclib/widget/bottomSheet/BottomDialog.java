package com.step.lclib.widget.bottomSheet;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.step.lclib.R;
import com.step.lclib.util.DisplayHelper;
import com.step.lclib.widget.dialog.LcDialogBase;

public class BottomDialog extends LcDialogBase {


    BottomSheetDialog bottomSheetDialog;
    private final static int ANIMATION_DURATION = 200;

    private View mContentView;

    private boolean mIsAnimating = false;

    private OnBottomSheetShowListener mOnBottomSheetShowListener;


    public BottomDialog(Context context) {
        super(context, R.style.LcDialog_BottomSheet);
    }


    public void setmOnBottomSheetShowListener(OnBottomSheetShowListener mOnBottomSheetShowListener) {
        this.mOnBottomSheetShowListener = mOnBottomSheetShowListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setPadding(0, 0, 0, 0);

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();

        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER;

        int screenWidth = DisplayHelper.getScreenWidth();
        int screenHeight = DisplayHelper.getScreenHeight();

        layoutParams.width = Math.min(screenWidth, screenHeight);

        getWindow().setAttributes(layoutParams);

        setCanceledOnTouchOutside(true);

    }


    @Override
    public void setContentView(int layoutResId) {
        mContentView = LayoutInflater.from(getContext()).inflate(layoutResId, null);
        super.setContentView(mContentView);
    }

    @Override
    public void setContentView(View view) {
        mContentView = view;
        super.setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContentView = view;
        super.setContentView(view, params);
    }

    public View getContentView() {
        return mContentView;
    }

    private void animateUp() {

        if (mContentView == null) return;

        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f
        );

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setInterpolator(new DecelerateInterpolator());
        animationSet.setDuration(ANIMATION_DURATION);
        animationSet.setFillAfter(true);

        mContentView.startAnimation(animationSet);

    }


    private void animateDown() {
        if (mContentView == null) return;

        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f
        );

        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);

        AnimationSet animationSet = new AnimationSet(true);

        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setInterpolator(new DecelerateInterpolator());
        animationSet.setDuration(ANIMATION_DURATION);
        animationSet.setFillAfter(true);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mIsAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIsAnimating = false;

                if (mContentView == null) return;

                mContentView.post(new Runnable() {
                    @Override
                    public void run() {
                        BottomDialog.super.dismiss();
                    }
                });


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mContentView.startAnimation(animationSet);
    }


    @Override
    public void show() {
        super.show();
        animateUp();
        if (mOnBottomSheetShowListener != null) {
            mOnBottomSheetShowListener.onShow();
        }
    }

    @Override
    public void dismiss() {
        if (mIsAnimating) return;
        animateDown();
    }

    public static class BottomSheetBuilder {
        private Context mContext;
        private View mRealView;
        private BottomDialog mBottomDialog;

        public BottomSheetBuilder(Context context) {
            mContext = context;
        }

        public BottomSheetBuilder setRealView(View mRealView) {
            this.mRealView = mRealView;
            return this;
        }

        public BottomDialog build() {
            mBottomDialog = new BottomDialog(mContext);
            mBottomDialog.setContentView(mRealView,
                    new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));

            return mBottomDialog;
        }
    }

    public interface OnBottomSheetShowListener {
        void onShow();
    }
}

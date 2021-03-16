package com.step.lclib.widget.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.IntDef;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.step.lclib.R;
import com.step.lclib.util.DisplayHelper;
import com.step.lclib.util.LayoutHelper;
import com.step.lclib.util.ResHelper;
import com.step.lclib.view.CornerLinearLayout;
import com.step.lclib.widget.progress.LcLoadingView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LcTipDialog extends LcDialogBase {

    public LcTipDialog(Context context) {
        this(context, R.style.LcDialog_Tip);
    }

    public LcTipDialog(Context context, int themeResId) {
        super(context, themeResId);
        setCanceledOnTouchOutside(false);
    }


    public static class Builder {
        /**
         * 不显示任何icon
         */
        public static final int ICON_TYPE_NOTHING = 0;
        /**
         * 显示 Loading 图标
         */
        public static final int ICON_TYPE_LOADING = 1;
        /**
         * 显示成功图标
         */
        public static final int ICON_TYPE_SUCCESS = 2;
        /**
         * 显示失败图标
         */
        public static final int ICON_TYPE_FAIL = 3;
        /**
         * 显示信息图标
         */
        public static final int ICON_TYPE_INFO = 4;

        @IntDef({ICON_TYPE_NOTHING, ICON_TYPE_LOADING, ICON_TYPE_SUCCESS, ICON_TYPE_FAIL, ICON_TYPE_INFO})
        @Retention(RetentionPolicy.SOURCE)
        public @interface IconType {
        }


        private @IconType
        int mCurrentIconType = ICON_TYPE_NOTHING;

        private Context mContext;

        private CharSequence mTipWord;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setIconType(@IconType int iconType) {
            mCurrentIconType = iconType;
            return this;
        }

        public Builder setTipWord(CharSequence tipWord) {
            mTipWord = tipWord;
            return this;
        }


        public LcTipDialog create() {
            return create(true);
        }

        public LcTipDialog create(boolean cancelable) {
            return create(cancelable, R.style.LcDialog_Tip);
        }

        public LcTipDialog create(boolean cancelable, int style) {
            LcTipDialog lcTipDialog = new LcTipDialog(mContext, style);
            lcTipDialog.setCancelable(cancelable);
            lcTipDialog.setCanceledOnTouchOutside(cancelable);


            Window window = lcTipDialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = WindowManager.LayoutParams.WRAP_CONTENT;
            attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
            attributes.gravity = Gravity.CENTER;
            attributes.dimAmount = 0.5f;
            //这个背景必须设置哦，否则 会出现对话框 宽度很宽
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            Context dialogContext = lcTipDialog.getContext();
            CornerLinearLayout cornerLinearLayout = new CornerLinearLayout(dialogContext);
            cornerLinearLayout.setViewOutline(DisplayHelper.dp2px(dialogContext, 10),
                    LayoutHelper.RADIUS_ALL);
            cornerLinearLayout.setBackgroundColor(
                    ResHelper.getAttrColor(dialogContext, R.color.color_75_pure_black));
            cornerLinearLayout.setOrientation(LinearLayout.VERTICAL);
            cornerLinearLayout.setGravity(Gravity.CENTER_HORIZONTAL);

            int deviceWidth = DisplayHelper.getScreenWidth(dialogContext);
            int deviceHeight = DisplayHelper.getScreenHeight(dialogContext);

            int width = deviceWidth / 3;
            int height = (int) (width * 1.5);

            cornerLinearLayout.setLayoutParams(new ViewGroup.LayoutParams(width, height));

            if (mCurrentIconType == ICON_TYPE_LOADING) {
                LcLoadingView lcLoadingView = new LcLoadingView(dialogContext);
                cornerLinearLayout.addView(lcLoadingView, new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            } else if (mCurrentIconType == ICON_TYPE_INFO ||
                    mCurrentIconType == ICON_TYPE_FAIL ||
                    mCurrentIconType == ICON_TYPE_SUCCESS) {
                AppCompatImageView imageView = new AppCompatImageView(dialogContext);
                if (mCurrentIconType == ICON_TYPE_SUCCESS) {
                    imageView.setImageResource(R.drawable.icon_notify_done);
                } else if (mCurrentIconType == ICON_TYPE_FAIL) {
                    imageView.setImageResource(R.drawable.icon_notify_error);
                } else if (mCurrentIconType == ICON_TYPE_INFO) {
                    imageView.setImageResource(R.drawable.icon_notify_info);
                }

                cornerLinearLayout.addView(imageView, new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            if (!TextUtils.isEmpty(mTipWord)) {
                AppCompatTextView tipView = new AppCompatTextView(dialogContext);
                tipView.setEllipsize(TextUtils.TruncateAt.END);
                tipView.setGravity(Gravity.CENTER);
                tipView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

                tipView.setTextColor(ResHelper.getAttrColor(dialogContext, R.color.color_black));
                tipView.setText(mTipWord);
                cornerLinearLayout.addView(tipView, new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            lcTipDialog.setContentView(cornerLinearLayout);
            return lcTipDialog;
        }

    }


}

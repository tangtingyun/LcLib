package com.step.lclib.widget.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.IntDef;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.step.lclib.R;
import com.step.lclib.util.DisplayHelper;
import com.step.lclib.util.LayoutHelper;
import com.step.lclib.view.CornerLinearLayout;
import com.step.lclib.widget.progress.LcLoadingView;

import org.devio.hi.library.util.HiRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LcTipDialog extends LcDialogBase {

    public LcTipDialog(Context context) {
        this(context, R.style.LcDialog_Tip);
    }

    public LcTipDialog(Context context, int themeResId) {
        super(context, themeResId);
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


            Context dialogContext = lcTipDialog.getContext();
            CornerLinearLayout cornerLinearLayout = new CornerLinearLayout(dialogContext);
            cornerLinearLayout.setViewOutline(DisplayHelper.dp2px(10), LayoutHelper.RADIUS_ALL);
            cornerLinearLayout.setBackgroundColor(HiRes.INSTANCE.getColor(R.color.color_75_pure_black));
            cornerLinearLayout.setOrientation(LinearLayout.VERTICAL);
            cornerLinearLayout.setGravity(Gravity.CENTER);

            int deviceWidth = DisplayHelper.getScreenWidth();

            int width = deviceWidth / 3;
            int height = (int) (width * 0.8);

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
                tipView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                tipView.setTextColor(HiRes.INSTANCE.getColor(R.color.color_white));
                tipView.setText(mTipWord);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.topMargin = DisplayHelper.dp2px(10);
                cornerLinearLayout.addView(tipView, params);
            }
            lcTipDialog.setContentView(cornerLinearLayout);
            return lcTipDialog;
        }

    }


}

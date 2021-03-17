package com.step.lclib.widget.dialog;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.LayoutInflaterCompat;

import com.step.lclib.R;
import com.step.lclib.util.DisplayHelper;
import com.step.lclib.util.LayoutHelper;
import com.step.lclib.view.CornerLinearLayout;
import com.step.lclib.widget.progress.LcLoadingView;

import org.devio.hi.library.util.HiRes;

public class LcDialog extends LcDialogBase {
    private Context mBaseContext;

    public LcDialog(Context context) {
        this(context, R.style.LcDialog_Dialog);
    }

    public LcDialog(Context context, int theme) {
        super(context, theme);
        mBaseContext = context;
        init();
    }

    private void init() {

    }

    public void showWithImmersiveCheck(Activity activity) {
        Window window = getWindow();
        if (window == null) {
            return;
        }

        Window activityWindow = activity.getWindow();
        int systemUiVisibility = activityWindow.getDecorView().getSystemUiVisibility();
        if ((systemUiVisibility & View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN) == View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN ||
                (systemUiVisibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == View.SYSTEM_UI_FLAG_FULLSCREEN) {
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            window.getDecorView().setSystemUiVisibility(
                    activity.getWindow().getDecorView().getSystemUiVisibility());
            super.show();
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        } else {
            super.show();
        }
    }


    public void showWithImmersiveCheck() {
        if (!(mBaseContext instanceof Activity)) {
            super.show();
            return;
        }
        Activity activity = (Activity) mBaseContext;
        showWithImmersiveCheck(activity);
    }


    public static class MessageBuilder {
        protected CharSequence mTitle;
        protected CharSequence mMessage;
        protected CharSequence mConfirmText;
        protected CharSequence mCancelText;

        private View.OnClickListener mConfirmListener;
        private View.OnClickListener mCancelListener;

        private Context mContext;


        public MessageBuilder(Context context) {
            mContext = context;
        }

        public MessageBuilder setTitle(CharSequence title) {
            this.mTitle = title;
            return this;
        }

        public MessageBuilder setMessage(CharSequence message) {
            this.mMessage = message;
            return this;
        }

        public MessageBuilder addConfirmAction(CharSequence confirmText, View.OnClickListener confirm) {
            this.mConfirmText = confirmText;
            this.mConfirmListener = confirm;
            return this;
        }

        public MessageBuilder addCancelAction(CharSequence cancelText, View.OnClickListener cancel) {
            this.mCancelText = cancelText;
            this.mCancelListener = cancel;
            return this;
        }

        public LcDialog create() {
            return create(true);
        }

        public LcDialog create(boolean cancelable) {
            return create(cancelable, R.style.LcDialog_Dialog);
        }


        public LcDialog create(boolean cancelable, int style) {
            LcDialog lcDialog = new LcDialog(mContext, style);
            lcDialog.setCancelable(cancelable);
            lcDialog.setCanceledOnTouchOutside(cancelable);
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_message, null, false);
            lcDialog.setContentView(inflate);
            return lcDialog;
        }
    }


}

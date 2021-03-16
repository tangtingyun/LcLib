package com.step.lclib.widget.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.Window;

import androidx.appcompat.app.AppCompatDialog;

public class LcDialogBase extends AppCompatDialog {

    public LcDialogBase(Context context, int theme) {
        super(context, theme);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void dismiss() {
        Context context = getContext();
        if (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return;
            }
            super.dismiss();
        } else {
            try {
                super.dismiss();
            } catch (Throwable throwable) {
                // todo
            }
        }
    }
}

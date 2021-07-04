package com.step.lclib.work.bestpractice;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomsheet.BottomSheetDialog;


import androidx.annotation.NonNull;

public class HeightBootSheetDialog extends BottomSheetDialog {
    public HeightBootSheetDialog(@NonNull Context context) {
        super(context);
    }

    public HeightBootSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }

    protected HeightBootSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int screenHeight = getScreenHeight(getContext());
        int dialogHeight;
        if (screenHeight == 0) {
            dialogHeight = screenHeight;
        } else {
            dialogHeight = ViewGroup.LayoutParams.MATCH_PARENT;
        }

        Window window = getWindow();
        assert window != null;

        // Set to immersive
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ((int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        500f, getContext().getResources().getDisplayMetrics()
                ))
        );
        window.setGravity(Gravity.BOTTOM);

        // The maximum height needs to be set
        // The height you want to subtract, the default maximum height of dialog is below the status bar
        //float reduceHeight = getContext().getResources().getDimension(R.dimen.xxx); 
        //window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,dialogHeight - reduceHeight);
        // The last step is necessary, otherwise BottomSheetDialog will be displayed in the middle of the screen, and a blank area will appear at the bottom
        //window.setGravity(Gravity.BOTTOM);
    }

    /**
     * Get the height of the screen
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point.y;
    }

}


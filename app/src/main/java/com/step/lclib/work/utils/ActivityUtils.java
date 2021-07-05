package com.step.lclib.work.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class ActivityUtils {

    public static boolean isActivityDead(Activity activity) {
        if (activity == null) return true;

        boolean isDead = activity.isFinishing();
        isDead = isDead || activity.isDestroyed();
        return isDead;
    }

    public static boolean isActivityDead(Fragment fragment) {
        if (fragment == null || fragment.getActivity() == null) {
            return true;
        }
        FragmentActivity activity = fragment.getActivity();
        return isActivityDead(activity);
    }

    public static boolean isActivityDead(Context context) {
        if (context instanceof Application) {
            return false;
        } else if (context instanceof Activity) {
            return isActivityDead((Activity) context);
        } else if (context instanceof ContextWrapper) {
            return isActivityDead(((ContextWrapper) context).getBaseContext());
        }
        return false;
    }
}

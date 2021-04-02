package com.step.lclib.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.step.lclib.IRemoteBitmap;
import com.step.lclib.R;

import java.lang.ref.WeakReference;

public class RemoteBitmapService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new BitmapBinder(this);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("MainActivity", "没有解绑么? ");
        return super.onUnbind(intent);
    }

    public static class BitmapBinder extends IRemoteBitmap.Stub {

        WeakReference<Context> mContent;

        public BitmapBinder(RemoteBitmapService remoteBitmapService) {
            mContent = new WeakReference<Context>(remoteBitmapService.getApplicationContext());
        }

        @Override
        public Bitmap getRemoteBitmap(String bitmapKey) throws RemoteException {
            Log.e("MainActivity", "是谁在唱歌?");
            return BitmapFactory.decodeResource(mContent.get().getResources(), R.drawable.aaa);
        }
    }
}

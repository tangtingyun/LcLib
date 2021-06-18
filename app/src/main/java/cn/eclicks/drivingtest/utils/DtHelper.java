package cn.eclicks.drivingtest.utils;

import android.content.Context;
import android.util.Log;

/**
 * Created by leo on 16/3/28.
 */
public class DtHelper {
    static {
        try {
            System.loadLibrary("DtEngine");
        } catch (Throwable e) {
            Log.e("DtHelper", "static initializer: " + e);
            e.printStackTrace();
        }
    }

    public static final int ENCRYPT = 0;
    public static final int DECRYPT = 1;

    public native static byte[] question(Context context, byte[] data, long time, int mode);

    public native static byte[] answer(Context context, byte[] data, long time, int mode);

    public native static byte[] comments(Context context, byte[] data, long time, int mode);

    public native static byte[] read(String path, long time);


    public native static byte[] hexStringToBytes(String hexString);

    public native static String bytesToHexString(byte[] bytes);


}

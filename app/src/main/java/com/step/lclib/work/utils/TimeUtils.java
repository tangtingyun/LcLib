package com.step.lclib.work.utils;

class TimeUtils {

    private TimeUtils() {
    }

    private long mLastCheckTime = 0;
    // 一个小时
    private static long CHECK_INTERVAL_TIME = 1000 * 60 * 60;

    public void checkToken() {

        if (mLastCheckTime <= 0 ||
                System.currentTimeMillis() - mLastCheckTime > CHECK_INTERVAL_TIME) {
            mLastCheckTime = System.currentTimeMillis();
            // check
        }

    }

    public static TimeUtils getInstance() {
        return TimeUtils.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static TimeUtils instance = new TimeUtils();
    }


    public static void register() {
        TimeUtils.getInstance().checkToken();
    }

    public static void unregister() {
        TimeUtils.getInstance().checkToken();
    }
}

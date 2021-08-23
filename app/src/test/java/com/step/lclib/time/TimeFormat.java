package com.step.lclib.time;


import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormat {

    @Test
    public void testTimeFormat() {
//        1602766727  2020.10.15 20:58
//        1602766684  2020.10.15 20:58
//        1600950102  2020.09.24 20:21
//        1600950077  2020.09.24 20:21

//1600863639   1628760523
//1600950077   1628760523
//1600950102   1628760523
//        test(1629256635); // ====


        test(1629266943);
        test(1629266861);
        test(1629266859);
        test(1629266851);
    }

    private static void test(long time) {
        String date = formatDate(time * 1000L, "yyyy.MM.dd HH:mm:ss");
        System.out.println(time + " ->  " + date);
    }

    /*
     * time 为毫秒
     * */
    public static String formatDate(long time, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date(time));
    }
}

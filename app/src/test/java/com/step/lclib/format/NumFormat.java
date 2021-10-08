package com.step.lclib.format;

import android.text.TextUtils;
import android.util.ArrayMap;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class NumFormat {


    @Test
    public void testTimeFormat() {
//        String ids = "209,564,856,902,972,1100,1101,1133,1141,1142,1167,1249,1269,1283,1288,1329,1605,1631,1706,2022,2050,2071,2077,2114,2214,2215,2243,2263,2277,2335,2359,2369,2398,2411,2413,2448,2450,2527,2537,2541,2543,2547,2548,2555,3168,3435,3670,3833,3834,3836,3858,3860,3867,3874,4005,4132,4364,4368,4916,5058,5804,5805,5806,10007,10015,10042,10045,10058,15292,15359,15365,15435,15512,15531,16010,16032,16035,16036,16037,16038,16040,16041,16042,16206,25152,25310,25311,25312,25313,25314,25584,25651,25759,26034,26036,26037,26040,26042,26043,26044,26045,26046,26048,26049,26050,26051,26052,26053,26054,26056,26057,26059,26060,26062,26065,26066,26067,26069,26071,26072,26074,26075,26076,26077,26080,26081,26082,26083,26084,26085,26086,26391,35308,48549,50001,50015,3052110,3052160,3052462,3052482,3052753,3052769,3052813,3052814,3052819,3052867,3052868,3052869";
        String ids = "";
        String[] splitIds = ids.split(",");
        System.out.println(" splitIds : ->  " + splitIds.length);
        List<String> idsList = new ArrayList(splitIds.length);
        Collections.addAll(idsList, splitIds);
        System.out.println(" idsList : ->  " + idsList.size());
        System.out.println(idsList);
    }


    @Test
    public void strTest() {
        String a = "hello";
        process(a);
        System.out.println(a);
        String intern = a.intern();
        // 不在不能随机的列表里  可以随机
        List<String> sNotRandomList = new ArrayList<>();
        sNotRandomList.add("100007");
        System.out.println(sNotRandomList.contains("100007"));
        System.out.println(sNotRandomList.contains(100007));
    }

    private void process(String a) {
        a = a + "bbb";
    }

    @Test
    public void biHashMapTest() {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.forcePut("A", "红楼梦");
        biMap.forcePut("B", "西游记");
        biMap.forcePut("C", "金瓶梅");
        biMap.forcePut("D", "三国演义");

        for (Map.Entry entry :
                biMap.entrySet()) {
            System.out.println(" key   -> " + entry.getKey() + " value -> " + entry.getValue());
        }

        BiMap<String, String> inverse = biMap.inverse();
        for (Map.Entry entry :
                inverse.entrySet()) {
            System.out.println(" key   -> " + entry.getKey() + " value -> " + entry.getValue());
        }

//        biMap.forEach(new BiConsumer<String, String>() {
//            @Override
//            public void accept(String s, String s2) {
//                System.out.println(" key   -> "+s);
//                System.out.println(" value -> "+s2);
//            }
//        });
    }




    @Test
    public void arrayMapTest() {
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        arrayMap.put("A", "红楼梦");
        arrayMap.put("B", "西游记");
        arrayMap.put("C", "金瓶梅");
        arrayMap.put("D", "三国演义");

        for (Map.Entry entry :
                arrayMap.entrySet()) {
            System.out.println(" key   -> " + entry.getKey() + " value -> " + entry.getValue());
        }

//        biMap.forEach(new BiConsumer<String, String>() {
//            @Override
//            public void accept(String s, String s2) {
//                System.out.println(" key   -> "+s);
//                System.out.println(" value -> "+s2);
//            }
//        });
    }
}

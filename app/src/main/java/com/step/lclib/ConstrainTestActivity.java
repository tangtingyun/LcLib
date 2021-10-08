package com.step.lclib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ConstrainTestActivity extends AppCompatActivity {


//    TextView textView= new TextView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constrain_test);

//        float density = Resources.getSystem().getDisplayMetrics().density;
//        float density1 = getResources().getDisplayMetrics().density;
//
//        TypedValue.applyDimension(1, 1.0f, Resources.getSystem().getDisplayMetrics());
//
//        textView.setTextSize(10);

        HashBiMap<String, String> biMap = HashBiMap.create(4);
        biMap.put("A", "错误");
        biMap.put("B", "正确");
        biMap.put("C", "错误-错误");
        biMap.put("D", "正确-正确");

        String a = biMap.get("A");

        BiMap<String, String> inverse = biMap.inverse();
        String error = inverse.get("错误");

    }
}
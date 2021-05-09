package com.step.lclib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

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
    }
}
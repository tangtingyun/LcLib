package com.step.lclib.work;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.step.lclib.R;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class WorkJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_java);


        findViewById(R.id.btn_java).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StorageCase.INSTANCE.testImage(WorkJavaActivity.this);


                PermissionDialog.request(new Character[]{'a', 'b', 'c', 'd', 'e'}, "", new OnPermissionResult() {
                    @Override
                    public void granted() {

                    }

                    @Override
                    public void rejected() {

                    }
                });
            }
        });
    }
}
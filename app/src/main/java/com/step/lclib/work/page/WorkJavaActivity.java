package com.step.lclib.work.page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.step.lclib.R;
import com.step.lclib.work.FlutterAudio;
import com.step.lclib.work.OnPermissionResult;
import com.step.lclib.work.PermissionDialog;
import com.step.lclib.work.StorageCase;
import com.step.lclib.work.utils.ActivityUtils;


/**
 * @author zhangyifei
 * @time 21/5/30 下午4:11
 * @description
 */
public class WorkJavaActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_java);


        findViewById(R.id.btn_java).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context bContext = null;

                System.out.println((bContext instanceof Context));

                System.out.println(ActivityUtils.isActivityDead(context));
              /*  StorageCase.INSTANCE.testImage(WorkJavaActivity.this);


                PermissionDialog.request(new Character[]{'a', 'b', 'c', 'd', 'e'}, "", new OnPermissionResult() {
                    @Override
                    public void granted() {

                    }

                    @Override
                    public void rejected() {

                    }
                });*/
            }
        });
    }
}
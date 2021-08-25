package com.step.lclib.work.jetpack;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.PathMeasure;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.step.lclib.R;

public class LifecycleActivity extends AppCompatActivity {

    MutableLiveData strLive = new MutableLiveData();
    LiveData strLive2 = new MutableLiveData();

    ThreadLocal m;
    ViewGroup viewGroup;
    View view;

    Looper looper;

    Handler handler;

    PathMeasure pathMeasure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        getWindow().getDecorView().invalidate();
        getWindow().getDecorView().requestLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLifecycle().addObserver(new LocationObserver2());

        strLive.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {

            }
        });

        strLive.postValue("aaa");


        MediatorLiveData<String> mediatorLiveData = new MediatorLiveData<>();
        mediatorLiveData.addSource(strLive, null);
        mediatorLiveData.addSource(strLive2, null);

        LocationViewModel locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);

        locationViewModel.getLocation();
    }

    @Nullable
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return super.onRetainCustomNonConfigurationInstance();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    class LocationObserver implements LifecycleObserver {
    }

    class LocationObserver2 implements LifecycleEventObserver {

        @Override
        public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
            Log.e("LocationObserver2", "onStateChanged: " + event);
        }
    }


    class LocationViewModel extends ViewModel {
        public void getLocation() {
        }
    }
}
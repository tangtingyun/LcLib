package com.step.lclib.work.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.step.lclib.R
import com.step.lclib.work.retrofit


interface WanApi {
    suspend fun getIndex();
}


object WanApiService: WanApi by retrofit.create(WanApi::class.java)

class FlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
    }
}
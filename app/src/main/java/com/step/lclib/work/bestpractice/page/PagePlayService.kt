package com.step.lclib.work.bestpractice.page

import com.step.lclib.work.retrofit
import retrofit2.http.GET

interface WanApi {
    @GET("/api/v2/random/category/GanHuo/type/Android/count/10")
    suspend fun getRandomAndroid(): String
}

object WanApiServiceAsync : WanApi by retrofit.create(WanApi::class.java)
package com.step.lclib.work

import com.step.lclib.work.utils.ensureDir
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://gank.io"

private val cacheFile by lazy {
    File(AppGlobals.getApplication().cacheDir, "webServiceApi").apply {
        ensureDir()
    }
}

val retrofit by lazy {
    Retrofit.Builder()
        .client(
            OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .cache(Cache(cacheFile, 1024 * 1024 * 1024))
                .addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        lclog(message)
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        )
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}
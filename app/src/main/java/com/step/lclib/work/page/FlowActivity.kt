package com.step.lclib.work.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.step.lclib.R
import com.step.lclib.work.lclog
import com.step.lclib.work.retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.http.GET


interface WanApi {
    @GET("/api/v2/banners")
    fun getIndex(): Call<String?>;
}

object WanApiService : WanApi by retrofit.create(WanApi::class.java)


class WanRepository {
    fun getBanner() = apiFlow {
        lclog("apiFlow 222 -->  ${Thread.currentThread()}")
        WanApiService.getIndex()
    }
}

//  https://qiita.com/kaleidot725/items/bcb41be03854b11aee61
// https://zenn.dev/chmod644/articles/fc304b7e2508de

@Suppress("BlockingMethodInNonBlockingContext")
fun <T> apiFlow(call: suspend () -> Call<T?>): Flow<Result<T?>> =
    flow {
        lclog("apiFlow 333 -->  ${Thread.currentThread()}")
        val response = call().execute()
        if (!response.isSuccessful) throw HttpException(response)
        emit(Result.success(value = response.body()))
    }.catch { it: Throwable ->
        lclog("apiFlow error --> $it")
        emit(Result.failure(it))
    }.onStart {
        lclog("apiFlow 444 -->  ${Thread.currentThread()}")
    }.conflate().flowOn(Dispatchers.IO)


class FlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)

        val repository = WanRepository()

        lifecycleScope.launch {
            lclog("apiFlow 111 ->   ${Thread.currentThread()}")
            repository.getBanner().collect { res ->
                lclog("apiFlow result -->  ${res.getOrNull()}")
                lclog("apiFlow 555 -->  ${Thread.currentThread()}")
            }
        }
    }
}
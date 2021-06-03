package com.step.lclib.work.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.step.lclib.R
import com.step.lclib.work.lclog
import com.step.lclib.work.retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.GET


interface WanApi {
    @GET("/api/v2/banners")
    fun getIndex(): Call<String>;
}


object WanApiService : WanApi by retrofit.create(WanApi::class.java)


class WanRepository {
    fun getBanner() = apiFlow {
        lclog("flow -->  ${Thread.currentThread()}")
        WanApiService.getIndex()
    }

}

fun <T> apiFlow(call: suspend () -> Call<T>): Flow<Result<T?>> =
    flow {
        val response = call().execute()
        if (!response.isSuccessful) throw HttpException(response)
        emit(Result.success(value = response.body()))
    }.catch { it: Throwable ->
        emit(Result.failure(it))
    }.onStart {

    }.conflate().flowOn(Dispatchers.IO)


fun <T> Call<T>.apiFlowCallback(): Flow<Result<T?>> =
    callbackFlow {
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    sendBlocking(Result.success(body))
                } else {
                    sendBlocking(Result.failure(HttpException(response)))
                }
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                sendBlocking(Result.failure(throwable))
            }
        })
        awaitClose { cancel() }
    }


private fun errorMsg(response: Response<R>): String? {
    val msg = response.errorBody()?.string()
    return if (msg.isNullOrEmpty()) {
        response.message()
    } else {
        msg
    }
}

class FlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)

        val repository = WanRepository()

        lifecycleScope.launch {
            repository.getBanner()
            lclog("act ->   ${Thread.currentThread()}")
        }
    }
}
package com.step.lclib.work

import com.step.lclib.R
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.GET
import kotlinx.coroutines.cancel as flowCancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking

interface FlowApiService {
    @GET("foo")
    suspend fun getFoo(): List<String>
}


class FlowRepository(private val api: FlowApiService) {

    fun getFoo(): Flow<List<String>> {
        return flow {
            // exectute API call and map to UI object
            val fooList = api.getFoo()
            emit(fooList)
        }.flowOn(Dispatchers.IO) // Use the IO thread for this Flow
    }
}

fun Any.call() = callbackFlow<String> {


}

fun flowFrom(api: FlowApiService): Flow<String> = callbackFlow {


    awaitClose { }
}

object FlowCase {


}




@Deprecated("没把握")
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
                flowCancel(CancellationException("API Error", throwable))
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
package com.step.lclib.work

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Callback
import retrofit2.http.GET


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
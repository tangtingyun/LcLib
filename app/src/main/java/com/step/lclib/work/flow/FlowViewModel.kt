package com.step.lclib.work.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.step.lclib.work.lclog
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.NullPointerException

// https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/
// https://juejin.cn/post/6937138168474894343
// https://developer.android.google.cn/kotlin/coroutines/coroutines-best-practices?hl=zh-cn
// https://reduxkotlin.org/introduction/getting-started
// https://github.com/Tinder
// https://github.com/reduxkotlin

class FlowViewModel : ViewModel() {

    val repository = WanRepository()

    lateinit var job: Job;

    fun getData() {
        job = viewModelScope.launch {
            delay(3000)
            repository.getRandomAndroid()
                .map { it }
                .flatMapConcat {
                    delay(3000)
                    it.getOrNull()?.let { json ->
                        val id = JSONObject(json).optJSONArray("data").optJSONObject(0)
                            .optString("_id")
                        repository.getDetail(id)
                    } ?: flowOf(Result.failure(NullPointerException()))
                }
                .collect { res ->
                    lclog("apiFlow result -->  ${res.getOrNull()}")
                }
        }

        job.cancel()
    }


    suspend fun cbflow() {
        callbackFlow<Int> {
            offer(1)
            send(2)
            awaitClose(

            )
        }

        var stateIn = flow<Int> {

        }.stateIn(viewModelScope)


        stateIn.collect {

        }
        MutableStateFlow(1)

        MutableSharedFlow<Int>(2, 2, BufferOverflow.DROP_LATEST).tryEmit(1)

    }


}
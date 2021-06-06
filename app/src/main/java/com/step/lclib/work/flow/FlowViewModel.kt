package com.step.lclib.work.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.step.lclib.work.lclog
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.NullPointerException

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

//        job.cancel()
    }
}
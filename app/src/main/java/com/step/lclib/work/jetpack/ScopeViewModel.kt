package com.step.lclib.work.jetpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.step.lclib.work.lclog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ScopeViewModel : ViewModel() {


    var job: Job? = null;

    fun checkPop() {
        job = viewModelScope.launch {
            flow<Int> {
                emit(1)
                delay(2000)
                emit(2)
            }.flowOn(Dispatchers.IO)
                .collect {
                    lclog("receive --> $it")
                }
        }
    }

    fun stopCheck() {
        job?.cancel()

    }
}
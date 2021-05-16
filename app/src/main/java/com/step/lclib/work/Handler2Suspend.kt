package com.step.lclib.work

import android.os.Handler
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


suspend fun <T> Handler.run(block: () -> T) = suspendCoroutine<T> { continuation ->
    post {
        try {
            continuation.resume(block())
        } catch (e: Exception) {
            continuation.resumeWithException(e)
        }
    }
}
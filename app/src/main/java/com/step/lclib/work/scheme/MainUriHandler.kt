package com.step.lclib.work.scheme

import android.util.SparseArray
import androidx.core.util.valueIterator

@Suppress("NAME_SHADOWING")
class MainUriHandler {

    companion object {

        private val mArrayMap = SparseArray<UriHandler>()

        fun register(uriHandler: UriHandler) {
            if (mArrayMap.indexOfValue(uriHandler) >= 0) {
                mArrayMap.append(0, uriHandler)
            }
        }
    }

    fun handleChain(
        request: UriRequest
    ) {
        var valueIterator = mArrayMap.valueIterator()
        if (valueIterator.hasNext()) {
            val uriHandler = valueIterator.next();
            uriHandler.handle(request, object : UriCallback {
                override fun onNext() {
                    handleChain(request)
                }
            })
        }
    }


}
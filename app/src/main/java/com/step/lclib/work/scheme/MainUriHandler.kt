package com.step.lclib.work.scheme

import android.util.ArrayMap

class MainUriHandler private constructor() : UriHandler() {

    companion object {
        val instance = SingletonProvider.holder
    }

    private object SingletonProvider {
        val holder = MainUriHandler()
    }


    private val mArrayMap = ArrayMap<UriRequest, UriHandler?>()

    fun register(uriRequest: UriRequest, uriHandler: UriHandler?) {
        if (mArrayMap.containsKey(uriRequest)) {
            mArrayMap[uriRequest] = uriHandler
        }
    }

    override fun handle(
        request: UriRequest,
        callback: UriCallback
    ) {
        val uriHandler = mArrayMap[request]
        uriHandler?.handle(request, object : UriCallback {
            override fun onNext() {
                callback.onNext()
            }

            override fun onComplete(resultCode: Int) {
                callback.onComplete(resultCode)
            }
        })
    }
}
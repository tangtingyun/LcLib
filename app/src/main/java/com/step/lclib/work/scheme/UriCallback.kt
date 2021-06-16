package com.step.lclib.work.scheme


interface UriCallback {

    companion object {

        const val CODE_SUCCESS = 200

        const val CODE_ERROR = 500
    }


    fun onNext()

    fun onComplete(resultCode: Int)
}

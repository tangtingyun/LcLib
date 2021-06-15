package com.step.lclib.work.scheme

object SchemeDispatch {


    fun openUri(request: UriRequest) {

        MainUriHandler.instance.handle(request, object : UriCallback {
            override fun onNext() {
                MainUriHandler.instance.handle(request, this)
            }

            override fun onComplete(resultCode: Int) {
            }
        })
    }
}

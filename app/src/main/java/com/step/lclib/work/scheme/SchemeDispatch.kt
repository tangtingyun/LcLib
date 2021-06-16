package com.step.lclib.work.scheme


//https://tech.meituan.com/2018/08/23/meituan-waimai-android-open-source-routing-framework.html
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

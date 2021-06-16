package com.step.lclib.work.scheme


//https://tech.meituan.com/2018/08/23/meituan-waimai-android-open-source-routing-framework.html
object SchemeDispatch {

    private var sHasRegister = false;

    fun openUri(request: UriRequest) {
        ensureInit();
        MainUriHandler().handleChain(request)
    }

    private fun ensureInit() {
        if (!sHasRegister) {
            init()
            sHasRegister = true;
        }
    }


    fun init() {
        MainUriHandler.register(DriveUriHandler())
    }
}

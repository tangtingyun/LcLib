package com.step.lclib.work.scheme


//https://tech.meituan.com/2018/08/23/meituan-waimai-android-open-source-routing-framework.html
object SchemeDispatch {


    private var sHasRegister = false;

    fun test() {
        UriRequest("https://gank.io").start()
    }

    fun openUri(request: UriRequest) {
        ensureInit();
        ChainUriHandler().handleChain(request)
    }

    private fun ensureInit() {
        if (!sHasRegister) {
            init()
            sHasRegister = true;
        }
    }


    fun init() {
        ChainUriHandler.register(DriveUriHandler())
    }

    private class ChainUriHandler {

        companion object {

            private val mArrayMap = ArrayList<UriHandler>()

            fun register(uriHandler: UriHandler) {
                if (!mArrayMap.contains(uriHandler)) {
                    mArrayMap.add(uriHandler)
                }
            }
        }

        fun handleChain(request: UriRequest) {
            next(mArrayMap.iterator(), request)
        }

        private fun next(iterator: MutableIterator<UriHandler>, request: UriRequest) {
            if (iterator.hasNext()) {
                val uriHandler = iterator.next();
                uriHandler.handle(request, object : UriCallback {
                    override fun onNext(uriRequest: UriRequest) {
                        next(iterator, uriRequest)
                    }
                })
            }
        }


    }
}

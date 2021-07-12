package com.step.lclib.work.utils

import android.webkit.CookieManager

// https://sourcegraph.com/github.com/react-native-cookies/cookies/-/blob/android/src/main/java/com/reactnativecommunity/cookies/CookieManagerModule.java?utm_source=share
// https://sourcegraph.com/github.com/Gamez0/WebView-with-cookieManager/-/blob/app/src/main/java/com/praylist/simpleqr/MainActivity.java?utm_source=share


/*
*   mdn 关于 cookies 的定义
*
*   https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Cookies
*
* */
class Cookies {

    fun sync() {
        CookieManager.getInstance().setCookie("", "")
        CookieManager.getInstance().flush()
    }
}




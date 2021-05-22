package com.step.lclib.work

object ThreadClassLoader {

    fun test() {
        lclog("${Thread.currentThread().contextClassLoader}")
    }
}
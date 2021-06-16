package com.step.lclib.work.scheme

abstract class UriHandler {
    abstract fun handle(request: UriRequest, callback: UriCallback)

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
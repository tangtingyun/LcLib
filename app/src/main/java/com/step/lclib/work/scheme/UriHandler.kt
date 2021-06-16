package com.step.lclib.work.scheme

abstract class UriHandler {
    abstract fun handle(request: UriRequest, callback: UriCallback)
}
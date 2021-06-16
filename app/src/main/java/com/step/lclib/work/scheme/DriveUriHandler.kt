package com.step.lclib.work.scheme

class DriveUriHandler : UriHandler() {
    override fun handle(request: UriRequest, callback: UriCallback) {

        if (request.uri.equals(DRIVE_SCHEME)) {

        } else {
            callback.onNext()
        }
    }

    companion object {
        const val DRIVE_SCHEME = "drive://test"
    }
}
// IRemoteBitmap.aidl
package com.step.lclib;

// Declare any non-default types here with import statements

interface IRemoteBitmap {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    Bitmap getRemoteBitmap(String bitmapKey);
}

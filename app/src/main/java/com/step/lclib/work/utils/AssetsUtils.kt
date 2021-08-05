package com.step.lclib.work.utils

import com.step.lclib.work.AppGlobals
import com.step.lclib.work.lclog

object AssetsUtils {
    fun readStr(name: String) {
        val open = AppGlobals.getApplication().assets.open(name)
        val readLines = open.reader().readLines()
        val (one, two, three, four) = readLines[0].split("|")
        lclog("$one , $two, $three, $four")
    }
}
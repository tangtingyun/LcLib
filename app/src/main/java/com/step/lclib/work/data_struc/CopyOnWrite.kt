package com.step.lclib.work.data_struc

import java.util.concurrent.CopyOnWriteArrayList

// https://xxgblog.com/2016/04/02/traverse-list-thread-safe/
internal class CopyOnWrite {
    var mList = CopyOnWriteArrayList<String>()
    private fun addBean(bean: String) {
        mList.add(bean)
    }

    private fun removeBean(bean: String) {
        mList.remove(bean)
    }

    private fun forEachOP() {
        for (bean in mList) {
            println(bean)
            if (bean === "1") {
                mList.remove(bean)
            }
        }

    }
}
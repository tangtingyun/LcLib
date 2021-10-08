package com.step.lclib.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CoroutineTest {
    @Test
    fun launchTest() = runBlocking {
        println("start")
        //创建一个全局作用域协程，不会阻塞当前线程，生命周期与应用程序一致
        GlobalScope.launch {
            //在这1000毫秒内该协程所处的线程不会阻塞
            //协程将线程的执行权交出去，该线程继续干它要干的事情，到时间后会恢复至此继续向下执行
            delay(1000)//1秒无阻塞延迟（默认单位为毫秒）
            println("GlobalScope.launch")
        }
        println("end")//主线程继续，而协程被延迟
    }
}
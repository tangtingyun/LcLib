package com.step.lclib.gson

import com.google.gson.Gson
import com.step.lclib.LoginDeviceModel
import org.junit.Test


//data class DefaultNull(var duration_order: String ="order_test", var name: String? = "name_test")
data class DefaultNull(var duration_order: String, var name: String = "name_test")
//data class DefaultNull()
//data class DefaultNull(var duration_order: String)

/*class DefaultNull {
    var duration_order: String = "order_test"
    var name: String = "name_test"
}*/

const val JSON_TEST = """{ "duration_order" : "aa" }"""

class GsonTest {

    @Test
    fun testNull() {
        var fromJson = Gson().fromJson(JSON_TEST, DefaultNull::class.java)
        println(fromJson)
//        println(fromJson.name.length)
    }


    @Test
    fun testKotlinStr() {
        val name = "name is null"
        val message = "message is null"
        val stackTrace = "stackTrace is null"
        var hashMapOf = mutableMapOf<String, String>()
        hashMapOf["name"] = name;
        hashMapOf["message"] = message;
        hashMapOf["stackTrace"] = stackTrace;
        val reportJson = """{"name":"$name", "message":"$message", "stackTrack":"$stackTrace"}"""
//        val reportJson = """{"name":$name, "message":$message, "stackTrack":$stackTrace}"""
//        println(reportJson)

        println(hashMapOf)
    }


    @Test
    fun reverse(){
        var hashMapOf = hashMapOf<String, String>()
    }

}
package com.step.lclib.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import com.step.lclib.LoginDeviceModel
import org.junit.Test


data class DefaultNull2(val duration_order: String, val name: String)

const val JSON_TEST_DEFAULT = """{ "duration_order" : "aa", "name" : null }"""

class GsonTest2 {

    @Test
    fun testNull() {
        val createGson = GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .setLenient()
            .create()
        var fromJson = createGson.fromJson(JSON_TEST_DEFAULT, DefaultNull2::class.java)
        println(fromJson)
        println(fromJson.name)
//        println(fromJson.name.length)
    }

}
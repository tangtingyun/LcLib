package com.step.lclib

import android.text.TextUtils
import android.view.View
import org.junit.Test

import org.junit.Assert.*
import java.lang.StringBuilder

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    private val sourceList: Array<String> = arrayOf(
        "主入口",
        "答题技巧",
        "答题技巧做题时",
        "精简题库",
        "顺序练习",
        "练习统计",
        "练习统计按钮",
        "考试结果统计不及格banner",
        "考试结果统计及格banner",
        "考试结果按钮",
        "关闭广告",
        "错题收藏按钮",
        "考试成绩汇总按钮",
        "定制模考卷按钮",
        "考试首页",
        "小浮层",
        "弹窗",
        "学员福利入口",
        "我的教练顶部菜单",
        "过期",
        "视频解析",
        "视频刷题",
        "顺序练习-VIP弹窗-火速开通点击",
        "错题",
        "最新错题",
        "视频详情页面",
        "保过卡"
    )

    private val keyList: Array<String> = arrayOf(
        "main_enter",
        "answer_skill",
        "answer_skill_practicing",
        "simplifyQuestion",
        "oderpractice",
        "oderpractice_statistical",
        "oderpractice_statistical_aways",
        "examResult_unpass",
        "examResult_pass",
        "examresult_aways",
        "close_adv",
        "wrongFav",
        "examRecord",
        "custom_practice_aways",
        "examWelcome",
        "small_float_layer",
        "campaign",
        "student_welfare_enter",
        "myCoachMenu",
        "overdue",
        "questionVideo",
        "video_question",
        "oderpractice_vip_tip",
        "all_wrong",
        "newest_wrong",
        "video_detail_all_vip",
        "baoguoka"
    )

    @Test
    fun getAllMap() {
        val map = mutableMapOf<String, String>()
        sourceList.forEachIndexed { index, element ->
            map[element] = keyList[index]
            println(
                """
                "$element" to "${keyList[index]}",
            """.trimIndent()
            )
        }

    }


    @Test
    fun testRun() {
        println("aaa" + getString(5))
        println("bbb" + getString(1))
        println("https".startsWith("http"))
        val dbName = "question_828.db"
//        val dbName = "192.168.1.1"
        val split = dbName.split(".")
        println(split.size)
        split.forEach {
            println(it)
        }
    }

    private fun getString(pos: Int): String {

        pos.takeIf { it > 2 }?.run {
            return "22"
        }
        return "44"
    }


    @Test
    fun testRange() {
        val position = 2;
        var map = mutableMapOf(
            IntRange(1, 3) to 1
        )
        println(map.get(IntRange(position, position)))
    }

    @Test
    fun testNull() {
        bindRemainHoursLayout(null)
    }

    private fun bindRemainHoursLayout(data: LoginDeviceModel?) {
        if (data?.duration_order != null) {
            print("ok")
        } else {
            print("not ok")
        }
    }


    @Test
    fun testCode() {
        val idsStr = "6";
        val resultText = idsStr.split(",").map {
            return@map when (it) {
                "1" -> "周一";
                "2" -> "周二";
                "3" -> "周三";
                "4" -> "周四";
                "5" -> "周五";
                "6" -> "周六";
                "7" -> "周日";
                else -> ""
            }
        }.joinToString(separator = "")

        val replaceStr = resultText.replace("周", "")

        println("周${replaceStr}")


        val replaceStr2 = "周一周三周五周日".replace("周", "")

        println(replaceStr2)

        val mSsWeek = StringBuilder()

        mSsWeek.append("周一")
        mSsWeek.append("周三")
        mSsWeek.append("周五")
        mSsWeek.append("周日")


        val replaceStr3 = mSsWeek.replace(Regex("周"), "");
        println("------$replaceStr3")
        mSsWeek.delete(0, mSsWeek.length)
        mSsWeek.append("周")
        mSsWeek.append(replaceStr3)

        println(mSsWeek)
    }

}

data class LoginDeviceModel(var duration_order: String? = "")

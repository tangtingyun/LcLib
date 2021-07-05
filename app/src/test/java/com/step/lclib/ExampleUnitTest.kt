package com.step.lclib

import org.junit.Test

import org.junit.Assert.*

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


}

package com.step.lclib.work.page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import com.step.lclib.R
import com.step.lclib.databinding.ActivityUtilsTestBinding
import com.step.lclib.work.lclog
import com.step.lclib.work.utils.SaveUriUtils

class UtilsTestActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityUtilsTestBinding
    private val NUM = "3345-78098";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityUtilsTestBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnSaveContract.setOnClickListener {
//            SaveUriUtils(this).addPhoneOnlyContact(arrayOf("测试"), arrayOf("110"), arrayOf("work"))


            if (!SaveUriUtils.compareHasPhoneNum(this, NUM)) {
                SaveUriUtils.addContact(this, "测试", NUM)
            } else {
                lclog("已经添加！")
            }


        }
    }
}
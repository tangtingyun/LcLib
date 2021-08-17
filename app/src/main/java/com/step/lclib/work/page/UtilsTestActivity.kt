package com.step.lclib.work.page

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.step.lclib.R
import com.step.lclib.databinding.ActivityUtilsTestBinding
import com.step.lclib.work.PermissionDialog
import com.step.lclib.work.SimplePermResult
import com.step.lclib.work.lclog
import com.step.lclib.work.utils.SaveUriUtils
import org.devio.hi.library.util.PermissionConstants
import java.util.*
import android.content.ContentProviderOperation
import java.lang.String


class UtilsTestActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityUtilsTestBinding
    private val NUM = "021-61278635";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityUtilsTestBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnSaveContract.setOnClickListener {
//            SaveUriUtils(this).addPhoneOnlyContact(arrayOf("测试"), arrayOf("110"), arrayOf("work"))

            PermissionDialog.requestContactNoTip(
                this,
                object : SimplePermResult() {
                    override fun granted() {
//                        if (!SaveUriUtils.compareHasPhoneNum(this@UtilsTestActivity, NUM)) {
//                            SaveUriUtils.addContact(this@UtilsTestActivity, "测试", NUM)
//                        } else {
//                            lclog("已经添加！")
//                        }
                        test1()
                    }

                    override fun rejected() {
                    }
                })
        }


        mBinding.btnGetAbi.setOnClickListener {
            lclog("ABI: ->  ${applicationInfo.nativeLibraryDir}")

            lclog("ABI List: ->  ${Arrays.toString(Build.SUPPORTED_ABIS)}")
            lclog("ABI 32List: ->  ${Arrays.toString(Build.SUPPORTED_32_BIT_ABIS)}")
            lclog("ABI 64List: ->  ${Arrays.toString(Build.SUPPORTED_64_BIT_ABIS)}")

            lclog("ABI cpu_abi  : ->  ${Build.CPU_ABI}")
            lclog("ABI cpu_abi2 : ->  ${Build.CPU_ABI2}")
            lclog("ABI cpu_abi2 : ->  primary:${Build.CPU_ABI}second:${Build.CPU_ABI2}")


            val ops: ArrayList<ContentProviderOperation> = ArrayList()
            ops.add(
                ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI)
                    .withSelection(ContactsContract.Data.RAW_CONTACT_ID + "=?",
                        arrayOf(String.valueOf(5))
                    )
                    .build()
            )

            contentResolver.applyBatch(ContactsContract.AUTHORITY, ops)

            val opsRaw: ArrayList<ContentProviderOperation> = ArrayList()
            opsRaw.add(
                ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI)
                    .withSelection(
                        ContactsContract.Data._ID + "=?",
                        arrayOf(String.valueOf(5))
                    )
                    .build()
            )

            contentResolver.applyBatch(ContactsContract.AUTHORITY, opsRaw)
        }
    }

    private fun test1() {

        lclog(
            "storage ->  ${
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            }"
        )
        lclog(
            "contact ->  ${
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_CONTACTS
                ) == PackageManager.PERMISSION_GRANTED
            }"
        )
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (!SaveUriUtils.compareHasPhoneNum(this, NUM)) {
                SaveUriUtils.addContact(this, "测试", NUM)
            } else {
                lclog("已经添加！")
            }

            if (!SaveUriUtils.compareHasPhoneNum(this, "789456123")) {
                SaveUriUtils.addContact(this, "测试", "789456123")
            } else {
                lclog("已经添加！")
            }
            if (!SaveUriUtils.compareHasPhoneNum(this, "18336092504")) {
                SaveUriUtils.addContact(this, "测试", "18336092504")
            } else {
                lclog("已经添加！")
            }
            if (!SaveUriUtils.compareHasPhoneNum(this, "789888456")) {
                SaveUriUtils.addContact(this, "红楼梦", "789888456")
            } else {
                lclog("已经添加！")
            }
            if (!SaveUriUtils.compareHasPhoneNum(this, "123369")) {
                SaveUriUtils.addContact(this, "西游记", "123369")
            } else {
                lclog("已经添加！")
            }
            if (!SaveUriUtils.compareHasPhoneNum(this, "7419852")) {
                SaveUriUtils.addContact(this, "水浒传", "7419852")
            } else {
                lclog("已经添加！")
            }
        }
    }
}
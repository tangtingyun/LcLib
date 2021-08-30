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
import android.content.Context
import android.content.pm.ActivityInfo
import android.provider.MediaStore
import android.text.TextUtils
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class UtilsTestActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityUtilsTestBinding
    private val NUM = "021-61278635";

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        lclog("ClBoostFlutterActivity:  onAttachedToWindow")
    }

    override fun onContentChanged() {
        super.onContentChanged()
        lclog("ClBoostFlutterActivity:  onContentChanged")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        lclog("ClBoostFlutterActivity:  onCreate")
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
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
                        addContact()
                    }

                    override fun rejected() {
                    }
                })
        }


        mBinding.btnGetAbi.setOnClickListener {
//            abiTest()
            lclog("asset 0 ->   ${TextUtils.isDigitsOnly("0")}")
            lclog("asset 1 ->   ${TextUtils.isDigitsOnly("1")}")
            lclog("asset 2 ->   ${TextUtils.isDigitsOnly("2")}")
            lclog("asset 2.2 ->   ${TextUtils.isDigitsOnly("2.2")}")
            lclog("asset 2.8 ->   ${TextUtils.isDigitsOnly("2.8")}")
            lclog("asset 2.866 ->   ${TextUtils.isDigitsOnly("2.866")}")
            lclog("asset 0.1 ->   ${TextUtils.isDigitsOnly("0.1")}")
            lclog("asset null ->   ${TextUtils.isDigitsOnly("")}")
//            lclog("asset ->   ${TextUtils.isDigitsOnly(null)}")
            var vipType = ""
            var vipTypeFix = 0;
            if (vipType != null) {
                try {
                    vipTypeFix = vipType.toInt();
                } catch (ex: NumberFormatException) {
                    lclog(ex.toString())
                }
            }

            lclog("fix ->  $vipTypeFix")
            lclog("fix try ->  ${"".toInt()}")

        }


        mBinding.btnStartResult.setOnClickListener {
            registerForActivityResult(object : ActivityResultContract<String, Boolean>() {
                override fun createIntent(context: Context, input: String?): Intent {
                    return Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        .putExtra(MediaStore.EXTRA_OUTPUT, input)
                }

                override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
                    return resultCode == RESULT_OK
                }

            }, object : ActivityResultCallback<Boolean> {
                override fun onActivityResult(result: Boolean?) {
                }

            }).launch("")

            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult(),
                object : ActivityResultCallback<ActivityResult> {
                    override fun onActivityResult(result: ActivityResult?) {
                    }

                }).launch(Intent(this, StorageActivity::class.java))
        }
    }

    private fun abiTest() {
        lclog("ABI: ->  ${applicationInfo.nativeLibraryDir}")
        lclog("ABI List: ->  ${Arrays.toString(Build.SUPPORTED_ABIS)}")
        lclog("ABI 32List: ->  ${Arrays.toString(Build.SUPPORTED_32_BIT_ABIS)}")
        lclog("ABI 64List: ->  ${Arrays.toString(Build.SUPPORTED_64_BIT_ABIS)}")
        lclog("ABI cpu_abi  : ->  ${Build.CPU_ABI}")
        lclog("ABI cpu_abi2 : ->  ${Build.CPU_ABI2}")
        lclog("ABI cpu_abi2 : ->  primary:${Build.CPU_ABI}second:${Build.CPU_ABI2}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        lclog("onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        lclog("onRestoreInstanceState")
    }

    private fun deleteContact() {
        val ops: ArrayList<ContentProviderOperation> = ArrayList()
        ops.add(
            ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI)
                .withSelection(
                    ContactsContract.Data.RAW_CONTACT_ID + "=?",
                    arrayOf("5")
                )
                .build()
        )

        contentResolver.applyBatch(ContactsContract.AUTHORITY, ops)

        val opsRaw: ArrayList<ContentProviderOperation> = ArrayList()
        opsRaw.add(
            ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI)
                .withSelection(
                    ContactsContract.Data._ID + "=?",
                    arrayOf("5")
                )
                .build()
        )

        contentResolver.applyBatch(ContactsContract.AUTHORITY, opsRaw)
    }

    private fun addContact() {

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
package com.step.lclib.work

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.step.lclib.R
import com.step.lclib.databinding.ActivityWorkBinding


class WorkActivity : AppCompatActivity() {

    private var mainHandler = Handler(Looper.getMainLooper())

    private lateinit var binding: ActivityWorkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        findViewById<View>(View.NO_ID)

//        if (savedInstanceState == null) {
//            val bundle = Bundle()
//            bundle.putInt("some_int", 0)
//            supportFragmentManager.beginTransaction()
//                .setReorderingAllowed(true)
//                .add(R.id.btn_dialog_cancel, BizDialogFragment::class.java, Bundle())
//                .commit()
//        }


        binding.btnPopup.setOnClickListener {
//            dialogTest()

//            storageTest()

//            setHtml()

            MediaCase.play(this, "bell/ding02.mp3")
            it.postDelayed({
                MediaCase.play(this, "bell/ding01.mp3")
            }, 200)
        }

    }

    private fun setHtml() {
        binding.tvHtml.apply {
            val fromHtml = HtmlCompat.fromHtml(
                """
                        <span style="color: #20B2AA;">HHHHHHH</span>
                """.trimIndent(),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            Log.e(AppConstant.TAG, fromHtml.toString())
            text = fromHtml
        }
    }

    private fun storageTest() {
        StorageCase.test(this)
    }

    private fun dialogTest() {

        var bizDialogFragment = BizDialogFragment()
        bizDialogFragment.show(supportFragmentManager, BizDialogFragment.TAG)

        Log.e(AppConstant.TAG, "onCreate: ${bizDialogFragment.isAdded}")
        Log.e(
            AppConstant.TAG,
            "onCreate: ${supportFragmentManager.findFragmentByTag(BizDialogFragment.TAG)}"
        )

        mainHandler.post {
            Log.e(
                AppConstant.TAG,
                "getMainThreadExecutor: ${bizDialogFragment.isAdded}"
            )

            Log.e(
                AppConstant.TAG,
                "getMainThreadExecutor: ${
                    supportFragmentManager.findFragmentByTag(
                        BizDialogFragment.TAG
                    )
                }"
            )
        }
    }
}
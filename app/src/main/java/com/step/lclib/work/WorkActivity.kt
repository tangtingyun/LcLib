package com.step.lclib.work

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.step.lclib.R
import com.step.lclib.databinding.ActivityWorkBinding


class WorkActivity : AppCompatActivity() {

    private var mainHandler = Handler(Looper.getMainLooper())

    private lateinit var binding: ActivityWorkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        if (savedInstanceState == null) {
//            val bundle = Bundle()
//            bundle.putInt("some_int", 0)
//            supportFragmentManager.beginTransaction()
//                .setReorderingAllowed(true)
//                .add(R.id.btn_dialog_cancel, BizDialogFragment::class.java, Bundle())
//                .commit()
//        }


        binding.btnPopup.setOnClickListener {
            var bizDialogFragment = BizDialogFragment()
            bizDialogFragment.show(supportFragmentManager, BizDialogFragment.TAG)

            StorageCase.test(this)

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
}
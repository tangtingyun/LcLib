package com.step.lclib.work.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.step.lclib.R
import com.step.lclib.databinding.ActivityJetpackBinding
import me.codeboy.android.aligntextview.util.CBAlignTextViewUtil

class JetpackActivity : AppCompatActivity() {
    lateinit var binding: ActivityJetpackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack)
        binding = ActivityJetpackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        *
        * 服务说明：
        * 虚拟商品不支持退换，购买即表示您同意《服务协议》，购买成功后，
        * 可观看科二考试项目所有视频，每个账号支持3台设备上登录使用，如有疑问联系：021-61278635。
        * */

//        binding.content2.setText("")
//        binding.content2.setText(null)

        binding.content.setText(CBAlignTextViewUtil.replacePunctuation(binding.content.text.toString()))

        val scopeViewModel = ViewModelProvider(this)[ScopeViewModel::class.java]
        binding.btnStart.setOnClickListener {
            scopeViewModel.checkPop()
        }

        binding.btnStop.setOnClickListener {
            scopeViewModel.stopCheck()
        }
    }
}
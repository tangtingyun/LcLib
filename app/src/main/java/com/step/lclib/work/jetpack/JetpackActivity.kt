package com.step.lclib.work.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.step.lclib.R
import com.step.lclib.databinding.ActivityJetpackBinding

class JetpackActivity : AppCompatActivity() {
    lateinit var binding: ActivityJetpackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack)
        binding = ActivityJetpackBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val scopeViewModel = ViewModelProvider(this)[ScopeViewModel::class.java]
        binding.btnStart.setOnClickListener {
            scopeViewModel.checkPop()
        }

        binding.btnStop.setOnClickListener {
            scopeViewModel.stopCheck()
        }
    }
}
package com.step.lclib.work.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.airbnb.lottie.TextDelegate
import com.step.lclib.R
import com.step.lclib.databinding.ActivityLottieBinding

class LottieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLottieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val textDelegate = TextDelegate(binding.dynamicTextView)
        binding.nameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                textDelegate.setText("NAME", s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        binding.dynamicTextView.setTextDelegate(textDelegate)
    }


}
package com.step.lclib.work.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.step.lclib.databinding.ActivityFlowOpCodeBinding
import com.step.lclib.work.lclog
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect

fun EditText.flowFrom(): Flow<String> = callbackFlow {
    val callback = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            s?.apply {
                trySendBlocking(this.toString())
                    .onFailure { throwable ->
                        lclog("$throwable")
                    }
            }
        }
    }

    addTextChangedListener(callback)

    awaitClose {
        lclog("call flow  close ----")
        removeTextChangedListener(callback)
    }

}

class FlowOpCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFlowOpCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenResumed {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {

            }
        }

        val launchWhenResumed = lifecycleScope.launchWhenResumed {
            val flowFrom = binding.etFlowCallback.flowFrom()
            flowFrom.collect {
                lclog("get data ->  $it")
            }
        }

        binding.btnFlowCancel.setOnClickListener {
            launchWhenResumed.cancel()
        }
    }
}

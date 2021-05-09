package com.step.lclib

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow {
        val listener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                offer(s)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }

        addTextChangedListener(listener)
        awaitClose {
            removeTextChangedListener(listener)
        }
    }
}

class LoginActivity : AppCompatActivity() {
    val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mainScope.launch {
            et_user_name.textChanges()
                .combine(et_pwd.textChanges()) { name, pwd -> (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) }
                .collect { allInput ->
//                    val (name, pwd) = pair;
                    Log.e("LoginActivity", "onCreate: " + allInput)
                }
        }

    }

    override fun onDestroy() {
        mainScope.cancel()
        super.onDestroy()
    }


}
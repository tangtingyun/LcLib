package com.step.lclib.work.flow

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import android.content.Intent

import android.content.ComponentName
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.step.lclib.work.lclog
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class FlowComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting("Android")
        }
    }

}

private fun testFlow(context: Context) {
    Toast.makeText(context, "Compose Show", Toast.LENGTH_SHORT).show()
    val intent = Intent()
    val cmp = ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI")
    intent.action = Intent.ACTION_MAIN
    intent.addCategory(Intent.CATEGORY_LAUNCHER)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent.component = cmp
    context.startActivity(intent)
}


@Composable
fun Greeting(name: String) {
    val context = LocalContext.current
    val inputValue = remember {
        mutableStateOf(TextFieldValue())
    }
    Column(Modifier.clickable { testFlow(context = context) }) {
        Text(text = "Hello $name!")
        Button(onClick = {
            testFlow(context = context)
        }) {
            Text(text = "callback Flow")
        }

        TextField(value = inputValue.value, onValueChange = {
            inputValue.value = it
        })

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Greeting("Android")
}
package com.step.lclib.debug.preference.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.step.lclib.R

class DebugPrefActivity : AppCompatActivity() {
    private var mEditable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug_pref)

        mEditable = intent.getBooleanExtra(EXTRA_EDITABLE, false)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_view, PrefListFragment.newInstance())
                .commit()
        }
    }


    fun showDetails(prefName: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container_view,
                PrefDetailsFragment.newInstance(prefName, mEditable)
            )
            .addToBackStack(null)
            .commit()
    }


    companion object {
        private const val EXTRA_EDITABLE = "extra_editable"

        fun start(context: Context, editable: Boolean) {
            val intent = Intent(context, DebugPrefActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra(EXTRA_EDITABLE, editable)
            context.startActivity(intent)
        }
    }
}


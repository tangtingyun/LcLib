package com.step.lclib.debug.preference.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.step.lclib.R

class DebugPrefActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug_pref)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true);
            setDisplayShowHomeEnabled(false);
            title = "PrefViewer";
        }

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
                PrefDetailsFragment.newInstance(prefName)
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack();
                } else {
                    finish()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, DebugPrefActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}


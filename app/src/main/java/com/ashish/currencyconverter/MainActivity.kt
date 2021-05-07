package com.ashish.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashish.currencyconverter.ui.main.MainFragment
import com.ashish.currencyconverter.utils.PrefUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PrefUtil.initialize(this)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}
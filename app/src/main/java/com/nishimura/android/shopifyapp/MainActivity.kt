package com.nishimura.android.shopifyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nishimura.android.shopifyapp.ui.collection.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

}

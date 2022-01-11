package com.raywenderlich.valorant_api.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.raywenderlich.valorant_api.R
import com.raywenderlich.valorant_api.ui.ui.maps.MapsListFragment

class MapsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.maps_list_activity)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MapsListFragment.newInstance())
                .commitNow()
        }
    }
}
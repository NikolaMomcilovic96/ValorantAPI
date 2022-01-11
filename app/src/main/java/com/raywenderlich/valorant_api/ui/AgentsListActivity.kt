package com.raywenderlich.valorant_api.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.raywenderlich.valorant_api.R
import com.raywenderlich.valorant_api.ui.ui.agents.AgentsFragment

class AgentsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.agents_list_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AgentsFragment.newInstance())
                .commitNow()
        }
    }
}
package com.raywenderlich.valorant_api.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.raywenderlich.valorant_api.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.agentsButton.setOnClickListener {
            startActivity(Intent(this, AgentsListActivity::class.java))
        }

        binding.mapsButton.setOnClickListener {
            startActivity(Intent(this, MapsListActivity::class.java))
        }
    }
}
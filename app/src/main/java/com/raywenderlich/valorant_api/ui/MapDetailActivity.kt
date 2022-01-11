package com.raywenderlich.valorant_api.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.raywenderlich.valorant_api.databinding.ActivityMapDetailBinding
import com.raywenderlich.valorant_api.repo.ValorantRepo
import com.raywenderlich.valorant_api.service.ValorantService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("ID").toString()

        val service = ValorantService.instance
        val repo = ValorantRepo(service)

        GlobalScope.launch {
            val map = repo.getCustomMap(id)
            if (map.isSuccessful) {
                withContext(Dispatchers.Main) {
                    binding.mapNameDetailTextView.text = map.body()?.data?.displayName
                    binding.mapCoordinateDetailTextView.text = map.body()?.data?.coordinates

                    Glide.with(binding.mapDetailImageView).load(map.body()?.data?.splash).into(binding.mapDetailImageView)
                    Glide.with(binding.siteDetailImageView).load(map.body()?.data?.displayIcon).into(binding.siteDetailImageView)
                }
            }
        }
    }
}
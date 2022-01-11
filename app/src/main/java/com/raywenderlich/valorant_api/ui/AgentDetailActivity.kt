package com.raywenderlich.valorant_api.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.raywenderlich.valorant_api.R
import com.raywenderlich.valorant_api.databinding.ActivityAgentDetailBinding
import com.raywenderlich.valorant_api.repo.ValorantRepo
import com.raywenderlich.valorant_api.service.ValorantService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AgentDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAgentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val agentId = intent.getStringExtra("ID").toString()

        val service = ValorantService.instance
        val repo = ValorantRepo(service)

        GlobalScope.launch {
            val agent = repo.getCustomAgent(agentId)
            Log.d("AGENT", agent.body().toString())
            if (agent.isSuccessful) {
                withContext(Dispatchers.Main) {
                    binding.nameTextView.text = agent.body()?.data?.displayName
                    binding.descriptionTextView.text = agent.body()?.data?.description
                    binding.roleTextView.text = agent.body()?.data?.role?.displayName
                    binding.roleDescriptionTextView.text = agent.body()?.data?.role?.description
                }
            }
        }
    }
}
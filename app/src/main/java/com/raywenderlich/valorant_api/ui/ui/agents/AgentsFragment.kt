package com.raywenderlich.valorant_api.ui.ui.agents

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.valorant_api.databinding.AgentsFragmentBinding
import com.raywenderlich.valorant_api.repo.ValorantRepo
import com.raywenderlich.valorant_api.service.Agent
import com.raywenderlich.valorant_api.service.ValorantService
import com.raywenderlich.valorant_api.ui.AgentDetailActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AgentsFragment : Fragment() {

    private lateinit var binding: AgentsFragmentBinding

    companion object {
        fun newInstance() = AgentsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AgentsFragmentBinding.inflate(inflater, container, false)

        val service = ValorantService.instance
        val repo = ValorantRepo(service)
        GlobalScope.launch {
            val allAgents = repo.getAllAgents()
            val agents = mutableListOf<Agent>()

            if (allAgents.isSuccessful) {
                allAgents.body()?.data?.forEach {
                    agents.add(it)
                    Log.d("SUCCESS", it.displayName)
                }
                withContext(Dispatchers.Main) {
                    binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    binding.recyclerView.adapter =
                        AgentSelectionRecyclerViewAdapter(agents) { agentId ->
                            if (agentId.isNotEmpty()) {
                                startActivity(
                                    Intent(
                                        activity,
                                        AgentDetailActivity::class.java
                                    ).putExtra("ID", agentId)
                                )
                            }
                        }
                }
            } else {
                Log.d("ERROR", allAgents.code().toString())
            }
        }
        return binding.root
    }

}
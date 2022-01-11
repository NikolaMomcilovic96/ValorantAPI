package com.raywenderlich.valorant_api.ui.ui.maps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.valorant_api.databinding.MapsListFragmentBinding
import com.raywenderlich.valorant_api.repo.ValorantRepo
import com.raywenderlich.valorant_api.service.Map
import com.raywenderlich.valorant_api.service.ValorantService
import com.raywenderlich.valorant_api.ui.MapDetailActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapsListFragment : Fragment() {

    private lateinit var binding: MapsListFragmentBinding

    companion object {
        fun newInstance() = MapsListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MapsListFragmentBinding.inflate(inflater, container, false)

        val service = ValorantService.instance
        val repo = ValorantRepo(service)
        GlobalScope.launch {
            val maps = repo.getAllMaps()
            val mapsList = mutableListOf<Map>()
            if (maps.isSuccessful) {
                maps.body()?.data?.forEach {
                    Log.d("MAP", it.displayName)
                    mapsList.add(it)
                }
                withContext(Dispatchers.Main) {
                    binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    binding.recyclerView.adapter =
                        MapsSelectionRecyclerViewAdapter(mapsList) { mapId ->
                            if (mapId.isNotEmpty()) {
                                startActivity(
                                    Intent(activity, MapDetailActivity::class.java)
                                        .putExtra("ID", mapId)
                                )
                            }
                        }
                }
            }
        }

        return binding.root
    }

}
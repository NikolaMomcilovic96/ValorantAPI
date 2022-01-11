package com.raywenderlich.valorant_api.ui.ui.maps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raywenderlich.valorant_api.databinding.MapsListItemBinding
import com.raywenderlich.valorant_api.service.Map
import kotlinx.android.synthetic.main.maps_list_item.view.*

class MapsSelectionRecyclerViewAdapter(
    private val maps: MutableList<Map>,
    private val onMapClickListener: (String) -> Unit
) : RecyclerView.Adapter<MapsSelectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapsSelectionViewHolder {
        val binding =
            MapsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MapsSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MapsSelectionViewHolder, position: Int) {
        holder.itemView.mapNameTextView.text = maps[position].displayName
        Glide.with(holder.itemView).load(maps[position].splash)
            .into(holder.itemView.mapSplashImageView)

        holder.itemView.cardView.setOnClickListener {
            onMapClickListener(maps[position].uuid)
        }
    }

    override fun getItemCount(): Int {
        return maps.size
    }
}
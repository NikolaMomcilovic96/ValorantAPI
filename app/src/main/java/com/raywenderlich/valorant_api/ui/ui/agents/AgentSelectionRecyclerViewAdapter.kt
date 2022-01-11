package com.raywenderlich.valorant_api.ui.ui.agents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raywenderlich.valorant_api.databinding.AgentListItemBinding
import com.raywenderlich.valorant_api.service.Agent
import kotlinx.android.synthetic.main.agent_list_item.view.*

class AgentSelectionRecyclerViewAdapter(
    private val agents: MutableList<Agent>,
    private val onAgentClickListener: (String) -> Unit
) : RecyclerView.Adapter<AgentSelectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentSelectionViewHolder {
        val binding = AgentListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AgentSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgentSelectionViewHolder, position: Int) {
        holder.itemView.textView2.text = agents[position].displayName
        holder.itemView.textView3.text = agents[position].description
        Glide.with(holder.itemView)
            .load(agents[position].displayIcon)
            .into(holder.itemView.imageView)

        holder.itemView.cardView.setOnClickListener {
            onAgentClickListener(agents[position].uuid)
        }
    }

    override fun getItemCount(): Int {
        return agents.size
    }
}
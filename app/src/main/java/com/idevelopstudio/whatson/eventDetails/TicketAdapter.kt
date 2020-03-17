package com.idevelopstudio.whatson.eventDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idevelopstudio.whatson.databinding.ListItemTicketTypeBinding
import com.idevelopstudio.whatson.models.TicketType

class TicketAdapter: ListAdapter<TicketType, TicketAdapter.ViewHolder>(TicketDiffCallback()) {

    class ViewHolder private constructor(val binding: ListItemTicketTypeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TicketType) {
            binding.ticket = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemTicketTypeBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketAdapter.ViewHolder {
            return ViewHolder.from(parent)
        }

        override fun onBindViewHolder(holder: TicketAdapter.ViewHolder, position: Int) {

            val item = getItem(position)
            holder.bind(item)


        }

    }

class TicketDiffCallback : DiffUtil.ItemCallback<TicketType>(){
    override fun areItemsTheSame(oldItem: TicketType, newItem: TicketType): Boolean {
        return oldItem === newItem
    }
    override fun areContentsTheSame(oldItem: TicketType, newItem: TicketType): Boolean {
        return oldItem.ticketId == newItem.ticketId
    }
}
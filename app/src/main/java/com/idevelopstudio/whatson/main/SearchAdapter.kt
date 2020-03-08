package com.idevelopstudio.whatson.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idevelopstudio.whatson.models.Event
import com.idevelopstudio.whatson.databinding.ListItemSearchCardBinding

class SearchAdapter : ListAdapter<Event, SearchAdapter.ViewHolder>(EventDiffCallback()){

    class ViewHolder private constructor(val binding: ListItemSearchCardBinding) : RecyclerView.ViewHolder(binding.root){
     fun bind(item: Event){
         binding.event = item
         binding.executePendingBindings()
     }

        companion object{
            fun from(parent: ViewGroup): SearchAdapter.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSearchCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class EventDiffCallback : DiffUtil.ItemCallback<Event>(){
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem === newItem
    }
    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }
}
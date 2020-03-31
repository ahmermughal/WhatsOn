package com.idevelopstudio.whatson.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idevelopstudio.whatson.models.Event
import com.idevelopstudio.whatson.databinding.ListItemSmallEventBinding

class EventAdapter(private val onClickListener: OnClickListener) : ListAdapter<Event, EventAdapter.ViewHolder>(EventDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ListItemSmallEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Event){
            binding.event = item
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSmallEventBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class OnClickListener(val clickListener: (topEvent:Event) -> Unit){
        fun onClick (topEvent: Event) = clickListener(topEvent)
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
package com.idevelopstudio.whatson.selectInterest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idevelopstudio.whatson.databinding.ListItemInterestsBinding
import com.idevelopstudio.whatson.models.Interest
import com.idevelopstudio.whatson.selectInterest.InterestsAdapter.ViewHolder

class InterestsAdapter: ListAdapter<Interest, ViewHolder>(InterestsDiffCallback()) {

    class ViewHolder private constructor(val binding: ListItemInterestsBinding): RecyclerView.ViewHolder (binding.root) {

        fun bind(item: Interest){
            //binding.date = item
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemInterestsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class InterestsDiffCallback : DiffUtil.ItemCallback<Interest>(){
    override fun areItemsTheSame(oldItem: Interest, newItem: Interest): Boolean {
        return oldItem === newItem
    }
    override fun areContentsTheSame(oldItem: Interest, newItem: Interest): Boolean {
        return oldItem.title == newItem.title
    }
}
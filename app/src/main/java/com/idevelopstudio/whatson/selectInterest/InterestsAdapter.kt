package com.idevelopstudio.whatson.selectInterest

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idevelopstudio.whatson.R
import com.idevelopstudio.whatson.databinding.ListItemInterestsBinding
import com.idevelopstudio.whatson.models.Interest
import com.idevelopstudio.whatson.selectInterest.InterestsAdapter.ViewHolder
import timber.log.Timber

class InterestsAdapter: ListAdapter<Interest, ViewHolder>(InterestsDiffCallback()) {

    class ViewHolder private constructor(val binding: ListItemInterestsBinding): RecyclerView.ViewHolder (binding.root) {

        fun bind(item: Interest){
            binding.interest = item
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

        holder.itemView.findViewById<ImageButton>(R.id.interest_button).setOnClickListener {
            if(item.selected){
                Timber.d("OnClickedPressed")
                it.setBackgroundResource(R.drawable.circle_dark_button)
                item.selected = false
            }else{
                Timber.d("OnClickedPressed")
                it.setBackgroundResource(R.drawable.circle_button)
                item.selected = true
            }
        }
        holder.bind(item)
    }

    fun returnList(): List<Interest>{
        return currentList
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
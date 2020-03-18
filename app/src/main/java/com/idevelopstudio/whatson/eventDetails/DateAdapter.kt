package com.idevelopstudio.whatson.eventDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idevelopstudio.whatson.databinding.ListItemDateBinding
import com.idevelopstudio.whatson.models.EventDate

class DateAdapter(private val onClickListener: OnClickListener) : ListAdapter<EventDate, DateAdapter.ViewHolder>(DateDiffCallback()) {

    class ViewHolder private constructor(val binding: ListItemDateBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: EventDate){
            binding.date = item
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemDateBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DateAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class OnClickListener(val clickListener: (date:EventDate) -> Unit){
        fun onClick (date: EventDate) = clickListener(date)
    }

}

class DateDiffCallback : DiffUtil.ItemCallback<EventDate>(){
    override fun areItemsTheSame(oldItem: EventDate, newItem: EventDate): Boolean {
        return oldItem === newItem
    }
    override fun areContentsTheSame(oldItem: EventDate, newItem: EventDate): Boolean {
        return oldItem.id == newItem.id
    }
}
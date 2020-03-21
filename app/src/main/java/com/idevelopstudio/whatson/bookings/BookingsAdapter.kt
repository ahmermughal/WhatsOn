package com.idevelopstudio.whatson.bookings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idevelopstudio.whatson.models.Event
import com.idevelopstudio.whatson.databinding.ListItemTopEventBinding
import com.idevelopstudio.whatson.models.UserBooking

class BookingsAdapter(private val onClickListener: OnClickListener) : ListAdapter<UserBooking, BookingsAdapter.ViewHolder>(BookingsDiffCallback()){

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

    class ViewHolder private constructor(val binding: ListItemTopEventBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: UserBooking){
            binding.event = item.event
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemTopEventBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
    class OnClickListener(val clickListener: (userBooking:UserBooking) -> Unit){
        fun onClick (userBooking: UserBooking) = clickListener(userBooking)
    }
}

class BookingsDiffCallback : DiffUtil.ItemCallback<UserBooking>(){
    override fun areItemsTheSame(oldItem: UserBooking, newItem: UserBooking): Boolean {
        return oldItem === newItem
    }
    override fun areContentsTheSame(oldItem: UserBooking, newItem: UserBooking): Boolean {
        return oldItem.id == newItem.id
    }


}
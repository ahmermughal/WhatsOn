package com.idevelopstudio.whatson.eventDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idevelopstudio.whatson.databinding.ViewPagerImageViewBinding

class SliderImageViewAdapter(val imageUrls: List<String>) : RecyclerView.Adapter<SliderImageViewAdapter.ViewHolder>() {

    class ViewHolder private constructor(val binding: ViewPagerImageViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String){
            binding.imageUrl = item
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewPagerImageViewBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = imageUrls.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageUrl = imageUrls[position]
        holder.bind(imageUrl)
    }
}
package com.idevelopstudio.whatson.onBoarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idevelopstudio.whatson.databinding.ViewPagerImageViewBinding
import com.idevelopstudio.whatson.databinding.ViewPagerOnBoardingBinding
import com.idevelopstudio.whatson.models.OnBoarding

class OnBoardingAdapter(private val onBoardingList: List<OnBoarding>) : RecyclerView.Adapter<OnBoardingAdapter.ViewHolder>() {

    class ViewHolder private constructor(val binding: ViewPagerOnBoardingBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OnBoarding){
            //binding.imageUrl = item
            binding.onBoardingBg.setImageDrawable(item.bg)
            binding.onBoardingTitle.text = item.title
            binding.onBoardingBody.text = item.body
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewPagerOnBoardingBinding.inflate(layoutInflater, parent, false)

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

    override fun getItemCount(): Int = onBoardingList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(onBoardingList[position])
    }
}
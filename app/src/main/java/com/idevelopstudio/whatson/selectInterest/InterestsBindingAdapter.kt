package com.idevelopstudio.whatson.selectInterest

import android.widget.ImageButton
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idevelopstudio.whatson.R
import com.idevelopstudio.whatson.models.Interest

@BindingAdapter("setInterestsList")
fun bindInterestsListToRecyclerView(recyclerView: RecyclerView, list: List<Interest>?){
    list?.let {
        val adapter = recyclerView.adapter as InterestsAdapter
        adapter.submitList(it)
    }
}

@BindingAdapter("setInterestButton")
fun bindInterestToButton(imageButton: ImageButton, interest: Interest?){
    interest?.let {
        if(!interest.selected){
            imageButton.setBackgroundResource(R.drawable.circle_dark_button)
        }else{
            imageButton.setBackgroundResource(R.drawable.circle_button)
        }
    }
}

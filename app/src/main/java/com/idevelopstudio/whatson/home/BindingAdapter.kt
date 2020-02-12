package com.idevelopstudio.whatson.home

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.idevelopstudio.whatson.Event

@BindingAdapter("topEventImage")
fun ImageView.setEventBackground(item: Event?){
    item?.let {
        setImageResource(item.image)
    }
}
package com.idevelopstudio.whatson.Utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.idevelopstudio.whatson.models.Event

@BindingAdapter("topEventImage")
fun ImageView.setEventBackground(item: Event?){
    item?.let {
        setImageResource(item.image)
    }
}

@BindingAdapter("eventName")
fun TextView.setEventName(item: Event?){
    item?.let {
        setText(it.title)
    }
}
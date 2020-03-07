package com.idevelopstudio.whatson.Utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idevelopstudio.whatson.home.EventAdapter
import com.idevelopstudio.whatson.home.TopEventsAdapter
import com.idevelopstudio.whatson.models.Event

@BindingAdapter("topEventListData")
fun bindTopEventRecyclerView(recyclerView: RecyclerView, data: List<Event>?){
    data?.let{
        val adapter = recyclerView.adapter as TopEventsAdapter
        adapter.submitList(it)
    }
}

@BindingAdapter("eventListData")
fun bindEventRecyclerView(recyclerView: RecyclerView, data: List<Event>?){
    data?.let{
        val adapter = recyclerView.adapter as EventAdapter
        adapter.submitList(it)
    }
}


@BindingAdapter("eventImage")
fun ImageView.setEventBackground(item: Event?){
    item?.let {
        Glide.with(this.context)
            .load(it.image[0])
            .into(this)
    }
}

@BindingAdapter("eventName")
fun TextView.setEventName(item: Event?){
    item?.let {
        setText(it.title)
    }
}
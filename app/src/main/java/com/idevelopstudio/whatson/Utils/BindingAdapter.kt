package com.idevelopstudio.whatson.Utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idevelopstudio.whatson.home.EventAdapter
import com.idevelopstudio.whatson.home.TopEventsAdapter
import com.idevelopstudio.whatson.models.Event
import timber.log.Timber

private const val BASE_URL="http://192.168.10.9:3000/"

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



@BindingAdapter("imageWithGlide")
fun bindImageViewWithGlide(imageView: ImageView, imageUrl : String?){
    imageUrl?.let {

        val fixedImageUrl = BASE_URL+imageUrl

        Glide.with(imageView)
            .load(fixedImageUrl)
            .into(imageView)
    }
}


@BindingAdapter("eventImage")
fun ImageView.setEventBackground(item: Event?){
    item?.let {
        val imageUrl = BASE_URL+it.images[0]
        Timber.d("ImageUrl: ${it.images[0]}")
        Glide.with(this.context)
            .load(imageUrl)
            .into(this)
    }
}

@BindingAdapter("eventName")
fun TextView.setEventName(item: Event?){
    item?.let {
        setText(it.title)
    }
}
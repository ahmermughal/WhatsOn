package com.idevelopstudio.whatson.bookings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idevelopstudio.whatson.home.TopEventsAdapter
import com.idevelopstudio.whatson.models.UserBooking

@BindingAdapter("bookingList")
fun bindRecyclerViewToAdapter(recyclerView: RecyclerView, bookingList: List<UserBooking>?){
    bookingList?.let {
        val adapter = recyclerView.adapter as BookingsAdapter
        adapter.submitList(bookingList)
    }
}
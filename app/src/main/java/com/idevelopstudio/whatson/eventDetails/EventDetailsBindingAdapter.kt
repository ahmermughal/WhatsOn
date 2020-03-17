package com.idevelopstudio.whatson.eventDetails

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idevelopstudio.whatson.models.EventDate
import com.idevelopstudio.whatson.models.TicketType

@BindingAdapter("dateListData")
fun bindDateRecyclerView(recyclerView: RecyclerView, data : List<EventDate>?){
    data?.let{
        val adapter = recyclerView.adapter as DateAdapter
        adapter.submitList(it)
    }
}

@BindingAdapter("ticketListData")
fun bindTicketRecyclerView(recyclerView: RecyclerView, data : List<TicketType>?){
    data?.let{
        val adapter = recyclerView.adapter as TicketAdapter
        adapter.submitList(it)
    }
}
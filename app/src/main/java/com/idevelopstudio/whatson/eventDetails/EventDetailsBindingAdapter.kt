package com.idevelopstudio.whatson.eventDetails

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idevelopstudio.whatson.models.EventDate
import com.idevelopstudio.whatson.models.TicketType
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

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

@BindingAdapter("setDate")
fun bindDateToTextView(textView: TextView, data: Long?){
    data?.let {
        Timber.d("Date: ${data}")
        val date = Date(data*1000L)
        val simpleDateFormat = SimpleDateFormat("EEE, d MMM", Locale.ENGLISH)
        textView.setText(simpleDateFormat.format(date).toString())
    }
}

@BindingAdapter("ticketName", "selectedTicket")
fun bindTicketNameTextView(textView: TextView, ticketName: String?, selectedAmount: Int?){

    ticketName?.let {ticketName->
        selectedAmount?.let {selectedAmount->
            textView.text = "$ticketName X $selectedAmount"
        }
    }

}
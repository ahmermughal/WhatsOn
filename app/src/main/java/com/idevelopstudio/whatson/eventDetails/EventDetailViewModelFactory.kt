package com.idevelopstudio.whatson.eventDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.idevelopstudio.whatson.models.TicketType

class EventDetailViewModelFactory (private val ticket: TicketType) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventDetailViewModel::class.java)){
            return EventDetailViewModel(ticket) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
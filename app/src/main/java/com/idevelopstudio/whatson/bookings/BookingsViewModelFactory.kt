package com.idevelopstudio.whatson.bookings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookingsViewModelFactory () : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookingsViewModel::class.java)){
            return BookingsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
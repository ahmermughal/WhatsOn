package com.idevelopstudio.whatson.bookings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idevelopstudio.whatson.models.UserBooking
import com.idevelopstudio.whatson.network.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class BookingsViewModel(uid: String) : ViewModel(){

    private val _bookings = MutableLiveData<List<UserBooking>>()
    val bookings : LiveData<List<UserBooking>>
    get() = _bookings

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob+ Dispatchers.Main)

    init {
        getAllBookingsOf(uid)
    }

    private fun getAllBookingsOf(uid: String) {
        coroutineScope.launch {
            try{
                _bookings.value = Api.retrofitService.getBookingsByUid(uid)
            }catch (t: Throwable){
                Timber.d(t.message!!)
            }
        }
    }

}
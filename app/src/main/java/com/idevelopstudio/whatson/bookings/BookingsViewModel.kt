package com.idevelopstudio.whatson.bookings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idevelopstudio.whatson.models.UserBooking
import com.idevelopstudio.whatson.network.Api
import com.idevelopstudio.whatson.utils.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber



class BookingsViewModel(uid: String) : ViewModel(){

    private val _bookings = MutableLiveData<List<UserBooking>>()
    val bookings : LiveData<List<UserBooking>>
    get() = _bookings

    private val _state = MutableLiveData<State>()
    val state : LiveData<State>
    get() = _state

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob+ Dispatchers.Main)

    init {
        getAllBookingsOf(uid)
        listIsNotEmpty()
    }


    private fun listIsEmpty(){
        _state.value = State.EMPTY
    }

    private fun listIsNotEmpty(){
        _state.value = State.NOT_EMPTY
    }

    private fun isLoading(){
        _state.value = State.LOADING
    }

    fun getAllBookingsOf(uid: String) {
        isLoading()
        coroutineScope.launch {
            try{
                _bookings.value = Api.retrofitService.getBookingsByUid(uid)

                if(_bookings.value.isNullOrEmpty()){
                    listIsEmpty()
                }else{
                    listIsNotEmpty()
                }
            }catch (t: Throwable){
                Timber.d(t.message!!)
            }
        }
    }

}
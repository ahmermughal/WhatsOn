package com.idevelopstudio.whatson.eventDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idevelopstudio.whatson.models.DefaultReponse
import com.idevelopstudio.whatson.models.TicketType
import com.idevelopstudio.whatson.network.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class EventDetailViewModel(val ticketType: TicketType): ViewModel() {

    private val _ticket = MutableLiveData<TicketType>()
    val ticket : LiveData<TicketType>
        get() = _ticket

    private val _selectedNumberOfTickets = MutableLiveData<Int>()
    val selectedNumberOfTickets :LiveData<Int>
        get() = _selectedNumberOfTickets

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _response = MutableLiveData<DefaultReponse>()
    val response : LiveData<DefaultReponse>
    get() = _response

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
    get() = _error

    init {
        _error.value = false
        _ticket.value = ticketType
        _selectedNumberOfTickets.value = 1
    }

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice : LiveData<Double>
    get() = _totalPrice

    fun incrementTicketCount(){
        _selectedNumberOfTickets.value = _selectedNumberOfTickets.value?.plus(1)
        setTotalPrice()
    }

    fun decrementTicketCount(){
        if(_selectedNumberOfTickets.value!! > 1) _selectedNumberOfTickets.value = _selectedNumberOfTickets.value?.minus(1)
        setTotalPrice()
    }

    private fun resetTicketCount(){
        _selectedNumberOfTickets.value = 1
    }

    private fun setTotalPrice(){
        _ticket.value?.let{
            _totalPrice.value = _selectedNumberOfTickets.value!!*it.price
        }
    }

    fun changeTicket(ticketType: TicketType){
        _ticket.value = ticketType
        resetTicketCount()
        setTotalPrice()
    }

    fun confirmBooking(uid: String, eventId: String, dayId: String){
        coroutineScope.launch {
            try {
                _response.value = Api.retrofitService.bookTicketByUid(uid, eventId, dayId, _ticket.value!!.ticketId, _selectedNumberOfTickets.value!!)
            }catch (t:Throwable){
                Timber.d(t.message)
                _error.value = true
            }
        }
    }
}
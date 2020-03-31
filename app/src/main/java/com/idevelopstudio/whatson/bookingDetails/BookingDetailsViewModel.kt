package com.idevelopstudio.whatson.bookingDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idevelopstudio.whatson.models.DefaultReponse
import com.idevelopstudio.whatson.network.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class BookingDetailsViewModel : ViewModel(){

    private val _response = MutableLiveData<DefaultReponse>()
    val response : LiveData<DefaultReponse>
    get() = _response

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun deleteBooking(uid: String, id: String){
        coroutineScope.launch {
            try {
                _response.value = Api.retrofitService.deleteBookingByUid(uid, id)
            }catch (t:Throwable){
                Timber.d(t.message!!)
            }
        }
    }

}

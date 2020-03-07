package com.idevelopstudio.whatson.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idevelopstudio.whatson.models.Event
import com.idevelopstudio.whatson.network.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val respose: LiveData<String>
    get() = _response

    init {
        getEventsData()
    }

    private fun getEventsData() {
    Api.retrofitService.getEvents().enqueue(object : Callback<List<Event>>{
        override fun onFailure(call: Call<List<Event>>, t: Throwable) {
            _response.value = "Failure: " + t.message
        }

        override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
            _response.value = "Success ${response.body()?.size} Total Events"
        }


    })
    }


}
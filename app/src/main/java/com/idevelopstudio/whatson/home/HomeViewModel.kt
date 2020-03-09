package com.idevelopstudio.whatson.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idevelopstudio.whatson.models.Event
import com.idevelopstudio.whatson.network.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel : ViewModel() {

    private val _response = MutableLiveData<List<Event>>()

    val response: LiveData<List<Event>>
        get() = _response

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getEventsData()
    }

    private fun getEventsData() {
        coroutineScope.launch {
            try {
                _response.value = Api.retrofitService.getEvents()
            } catch (t: Throwable) {
                Timber.d(t.message!!)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
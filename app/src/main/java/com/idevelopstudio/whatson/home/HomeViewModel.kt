package com.idevelopstudio.whatson.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.idevelopstudio.whatson.models.Event
import com.idevelopstudio.whatson.models.EventList
import com.idevelopstudio.whatson.models.InterestsWithEvents
import com.idevelopstudio.whatson.network.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel : ViewModel() {

    private val _interestsWithEvents = MutableLiveData<List<InterestsWithEvents>>()
    val interestsWithEvents: LiveData<List<InterestsWithEvents>>
        get() = _interestsWithEvents

    private val _topEvents = MutableLiveData<EventList>()
    val topEvents : LiveData<EventList>
    get() = _topEvents

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getEventsData()
    }

    private fun getEventsData() {
        coroutineScope.launch {
            try {
                _topEvents.value = EventList(Api.retrofitService.getEvents())
                _interestsWithEvents.value = Api.retrofitService.getAllUserInterestsWithEventsByUid(FirebaseAuth.getInstance().uid!!)
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
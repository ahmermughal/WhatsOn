package com.idevelopstudio.whatson.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idevelopstudio.whatson.utils.State
import com.idevelopstudio.whatson.models.Event
import com.idevelopstudio.whatson.network.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber



class MainViewModel: ViewModel() {

    private val _searchedEvents = MutableLiveData<List<Event>>()
    val searchEvents : LiveData<List<Event>>
        get() = _searchedEvents

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _searching = MutableLiveData<Boolean>()
    val searching : LiveData<Boolean>
        get() = _searching

    private val _state = MutableLiveData<State>()
    val state : LiveData<State>
    get() = _state

    init {
        isNotSearching()
    }

    fun isNotSearching(){
        _searching.value = false
        _state.value = State.NOT_SEARCHING
    }

    private fun isSearching(){
        _searching.value = true
        _state.value = State.NOT_EMPTY
    }

    private fun listIsEmpty(){
        _state.value = State.EMPTY
    }

    private fun isLoading(){
        _state.value = State.LOADING
    }



    fun searchEventsByTitle(title: String){
        isLoading()
        coroutineScope.launch {
            try {
                _searchedEvents.value = Api.retrofitService.searchEventsByTitle(title)
                if(_searchedEvents.value!!.isEmpty()){
                    Timber.d("listIsEmpty")
                    listIsEmpty()
                }else{
                    Timber.d("listIsNOTEmpty")
                    isSearching()
                }
            }catch (t:Throwable){
                Timber.d(t.message!!)
            }
        }
    }

}
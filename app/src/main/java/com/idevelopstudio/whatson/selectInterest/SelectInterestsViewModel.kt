package com.idevelopstudio.whatson.selectInterest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.idevelopstudio.whatson.models.Interest
import com.idevelopstudio.whatson.network.Api
import com.idevelopstudio.whatson.network.Apis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class SelectInterestsViewModel: ViewModel() {

    private val _interestsList = MutableLiveData<List<Interest>>()
    val interestsList : LiveData<List<Interest>>
    get() = _interestsList

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getInterestsList()
    }

    fun updateInterestList(list: List<Interest>){
        val idList = mutableListOf<String>()
        for(item in list){
            if(item.selected){
                idList.add(item.id)
            }
        }
        coroutineScope.launch {
            try{
                Api.retrofitService.updateUserInterests(FirebaseAuth.getInstance().uid!!, idList)
            }catch (t:Throwable){
                Timber.d(t.message!!)
            }
        }
    }

    private fun getInterestsList() {
        coroutineScope.launch {
            try {
                _interestsList.value = Api.retrofitService.getAllInterests()
            }catch (t: Throwable){
                Timber.d(t.message!!)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
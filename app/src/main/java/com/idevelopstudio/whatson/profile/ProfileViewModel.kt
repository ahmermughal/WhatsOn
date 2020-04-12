package com.idevelopstudio.whatson.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.idevelopstudio.whatson.models.EventUser
import com.idevelopstudio.whatson.models.Interest
import com.idevelopstudio.whatson.network.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class ProfileViewModel : ViewModel(){

    private val _user = MutableLiveData<EventUser>()
    val user : LiveData<EventUser>
    get() = _user

    private val _userInterestList = MutableLiveData<List<Interest>>()
    val userInterestList : LiveData<List<Interest>>
    get() =  _userInterestList

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getUserData()
    }

    fun getUserData() {
        coroutineScope.launch {
            try{
                _user.value = Api.retrofitService.getUser(FirebaseAuth.getInstance().uid!!)
                _userInterestList.value = getAllSelectedInterests(user.value!!.userInterests)
            }catch (t: Throwable){
                Timber.d(t.message!!)
            }
        }
    }

    private suspend fun getAllSelectedInterests(list: List<Interest>): List<Interest> {
        var newList = ArrayList<Interest>()
        for (item in list){
            if(item.selected){
                newList.add(item)
                Timber.d(item.title)
            }
        }
        return newList
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}
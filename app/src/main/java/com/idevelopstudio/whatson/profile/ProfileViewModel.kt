package com.idevelopstudio.whatson.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.idevelopstudio.whatson.models.EventUser
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

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getUserData()
    }

    private fun getUserData() {
        coroutineScope.launch {
            try{
                _user.value = Api.retrofitService.getUser(FirebaseAuth.getInstance().uid!!)
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
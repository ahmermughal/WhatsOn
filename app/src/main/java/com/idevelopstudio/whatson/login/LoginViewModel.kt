package com.idevelopstudio.whatson.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.idevelopstudio.whatson.models.DefaultReponse
import com.idevelopstudio.whatson.network.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel  : ViewModel(){

    private val _response = MutableLiveData<DefaultReponse>()
    val response : LiveData<DefaultReponse>
    get() = _response

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun createUser(user: FirebaseUser){
        coroutineScope.launch {
            try{
                _response.value = Api.retrofitService.createUser(user.uid, user.displayName ?: "Event User", user.email!!)
            }catch (t: Throwable){
                Timber.d("ERROR Create User: ${t}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
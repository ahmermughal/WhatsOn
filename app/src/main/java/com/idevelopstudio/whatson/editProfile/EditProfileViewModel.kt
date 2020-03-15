package com.idevelopstudio.whatson.editProfile

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

class EditProfileViewModel: ViewModel() {

    private val _response = MutableLiveData<DefaultReponse>()
    val response : LiveData<DefaultReponse>
    get() = _response

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun updateUser(uid: String, name: String, gender: String, phone: String, age: Int){
        coroutineScope.launch {
            try{
                _response.value = Api.retrofitService.updateUserData(uid, name, phone, age, gender)
            }catch (t: Throwable){
                Timber.d("ERROR Update User: ${t.message}")
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
package com.idevelopstudio.whatson.models

import com.squareup.moshi.Json

data class EventUser(
    @Json(name = "_id") val id: String,
    val name : String,
    val phone: String,
    //val dob : String,
    //val gender : String,
    val email : String,
    val uid: String
    )
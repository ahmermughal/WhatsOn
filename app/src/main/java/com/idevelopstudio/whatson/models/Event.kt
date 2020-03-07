package com.idevelopstudio.whatson.models

import com.squareup.moshi.Json

data class Event(
    @Json(name = "_id") val id : String,
    val title: String,
//    val picInt: Int
//    val description: String?,
//    val address: String?,
//    val type: String?,
   val image: List<String>
//    val organizer: String?
    )
package com.idevelopstudio.whatson.models

import com.squareup.moshi.Json

data class Event(
    @Json(name = "_id") val id : Int,
    val title: String,
    val image: Int
//    val description: String?,
//    val address: String?,
//    val type: String?,
//    val images: List<String>?,
//    val organizer: String?
    )
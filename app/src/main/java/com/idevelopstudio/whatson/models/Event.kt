package com.idevelopstudio.whatson.models

import com.squareup.moshi.Json

data class Event(
    @Json(name = "_id") val id : String,
    val title: String,
    val images: List<String>,
    val currentBookings: Int,
    val description: String,
    val address: String,
    val lat: Long,
    val long: Long,
    val type: String,
    val generalInfo: String,
    val ticketDetails: String,
    val organizerName: String,
    val days: List<EventDays>
    )

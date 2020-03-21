package com.idevelopstudio.whatson.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    @Json(name = "_id") val id : String,
    val title: String,
    val images: List<String>,
    val currentBookings: Int?,
    val description: String,
    val address: String,
    val lat: Double,
    val long: Double,
    val interest: String?,
    val generalInfo: String,
    val ticketDetails: String,
    //val organizer: String,
    val days: List<EventDate>?
    ) : Parcelable

@Parcelize
data class EventDate(
    @Json(name = "_id") val id: String,
    val day: Long,
    @Json(name = "typesOfTicket") val ticketTypes: List<TicketType>
) : Parcelable

@Parcelize
data class TicketType(
    @Json(name = "_id") val ticketId: String,
    val ticket: String,
    @Json(name = "noTickets") val totalTickets: Int,
    val ticketsLeft: Int,
    val price: Double
) : Parcelable
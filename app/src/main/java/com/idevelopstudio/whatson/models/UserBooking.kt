package com.idevelopstudio.whatson.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserBooking(
    @Json(name= "_id") val id: String,
    val user: String,
    val event: Event,
    val bookingDetails: BookingDetails
) : Parcelable

@Parcelize
data class BookingDetails(
    val day : Long,
    val ticketDetails: String,
    val ticketPrice: Double,
    val ticketBooked: Int
): Parcelable

//data class BookingEvent(
//    val images: List<String>,
//
//)
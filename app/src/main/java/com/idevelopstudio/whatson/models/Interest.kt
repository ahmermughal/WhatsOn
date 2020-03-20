package com.idevelopstudio.whatson.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Interest (
    @Json(name = "_id") val id: String,
    val title: String,
    var selected: Boolean
) : Parcelable


@Parcelize
data class InterestsList(
    val interestList: List<Interest>
) : Parcelable
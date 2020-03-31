package com.idevelopstudio.whatson.models

import com.squareup.moshi.Json

data class InterestsWithEvents(
    @Json(name = "interest") val interestTitle: String,
    val events: List<Event>?
)
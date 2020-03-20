package com.idevelopstudio.whatson.network

import com.idevelopstudio.whatson.models.*
import retrofit2.http.*

interface Apis{
    @GET("events")
    suspend fun getEvents():
            List<Event>

    @FormUrlEncoded
    @POST("users")
    suspend fun createUser(
        @Field("uid") uid: String,
        @Field("name") name: String,
        @Field("email") email: String
    ) : DefaultReponse

    @GET("users/{uid}")
    suspend fun getUser(
        @Path("uid")  uid: String
    ): EventUser

    @FormUrlEncoded
    @PATCH("users/{uid}")
    suspend fun updateUserData(
        @Path("uid") uid: String,
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("age") age: Int,
        @Field("gender") gender: String
    ) : DefaultReponse

    @GET("events/interests")
    suspend fun getAllInterests() : List<Interest>

    @FormUrlEncoded
    @POST("bookings/{uid}")
    suspend fun bookTicketByUid(
        @Path("uid") uidPath: String,
        @Field("eventId") eventId: String,
        @Field("dayId") dayId: String,
        @Field("typeId") typeId: String,
        @Field("noTickets") noTickets: Int
    ): DefaultReponse

    @GET("bookings/{uid}")
    suspend fun getBookingsByUid(
        @Path("uid") uid: String
    ) : List<UserBooking>

}
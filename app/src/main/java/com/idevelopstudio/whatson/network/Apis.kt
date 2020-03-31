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

    @FormUrlEncoded
    @PATCH("users/{uid}/interests")
    suspend fun updateUserInterests(
        @Path("uid") uid: String,
        @Field("interests") interests: List<String>
    ) : DefaultReponse

    @DELETE("bookings/{uid}/details/{id}")
    suspend fun deleteBookingByUid(
        @Path("uid") uid: String,
        @Path("id") id: String
    ): DefaultReponse

    @GET("users/{uid}/interests/list")
    suspend fun getAllUserInterestsWithEventsByUid(
        @Path("uid") uid:String
    ): List<InterestsWithEvents>

    @GET("events/search/{title}")
    suspend fun searchEventsByTitle(
        @Path("title") title:String
    ): List<Event>?

}
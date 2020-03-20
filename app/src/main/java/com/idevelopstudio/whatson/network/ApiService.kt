package com.idevelopstudio.whatson.network

import com.idevelopstudio.whatson.models.DefaultReponse
import com.idevelopstudio.whatson.models.Event
import com.idevelopstudio.whatson.models.EventUser
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


private const val BASE_URL="http://192.168.10.9:3000/"
private const val TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6ImFpdHNhbSIsImVtYWlsIjoiYWl0c2FtQGlkZXZlbG9wc3R1ZGlvLmNvbSJ9LCJpYXQiOjE1ODMwNjg4MTJ9.YIr_GadGJEy-RrEYo956oTvxp5RUZf-d8YC4aUX79qw"
//private const val BASE_URL = "https://mars.udacity.com/"

private val moshi= Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val original: Request = chain.request()
        val requestBuilder: Request.Builder = original.newBuilder()
            .addHeader("Authorization", "Bearer $TOKEN")
            .method(original.method(), original.body())
        val request: Request = requestBuilder.build()
        chain.proceed(request)
    }.build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

object Api{
    val retrofitService: Apis by lazy{
        retrofit.create(Apis::class.java)
    }
}

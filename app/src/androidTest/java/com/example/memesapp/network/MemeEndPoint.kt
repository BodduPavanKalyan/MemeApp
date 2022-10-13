package com.example.memesapp.network

import com.example.memesapp.model.Jokes
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MemeEndPoint {
    @GET("/get_memes")
    fun getMemes(): Call<Response<Jokes>>
}
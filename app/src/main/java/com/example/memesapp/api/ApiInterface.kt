package com.example.memesapp.api

import com.example.memesapp.model.Jokes
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/get_memes")

     suspend fun getJokes():Response<Jokes>
}
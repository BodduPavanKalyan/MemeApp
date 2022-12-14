package com.example.memesapp.network

import com.example.memesapp.model.Jokes
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class  MemeService  constructor(private val retrofit : Retrofit): MemeEndPoint {
    private val endpoint by lazy {  retrofit.create(MemeEndPoint::class.java)  }
    override fun getMemes() : Call<Response<Jokes>> {
        return endpoint.getMemes()
    }
}
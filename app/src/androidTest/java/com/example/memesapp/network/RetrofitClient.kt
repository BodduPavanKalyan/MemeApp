package com.example.memesapp.network

import com.example.memesapp.api.ApiUtility
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiUtility.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
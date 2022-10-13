package com.example.memesapp

import android.app.Application
import com.example.memesapp.api.ApiInterface
import com.example.memesapp.api.ApiUtility
import com.example.memesapp.repository.MemesRepository
import com.example.memesapp.room.MemeDatabase

class MyApplication:Application() {

    lateinit var memesRepository:MemesRepository
    override fun onCreate() {

        super.onCreate()
        val apiInterface= ApiUtility.getInstance().create(ApiInterface::class.java)
      val database=MemeDatabase.getDatabase(applicationContext)
        memesRepository=MemesRepository(apiInterface,database,applicationContext)
    }

}
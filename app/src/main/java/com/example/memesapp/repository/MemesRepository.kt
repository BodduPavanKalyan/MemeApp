package com.example.memesapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.memesapp.api.ApiInterface
import com.example.memesapp.model.Data
import com.example.memesapp.model. Jokes
import com.example.memesapp.model.Meme
import com.example.memesapp.room.MemeDatabase
import com.example.memesapp.util.MyUtils

class MemesRepository(
    private val apiInterface: ApiInterface,
    private val memeDatabase: MemeDatabase,
    private val applicationContext: Context
) {

    private  val memesLiveData= MutableLiveData<Jokes>()

    val memes:LiveData<Jokes>
    get()=memesLiveData

    suspend fun getMemes(){
        if(MyUtils.isInternetAvailable(applicationContext)){
            val result=apiInterface.getJokes()
            if(result.body()!=null){
                memeDatabase.memeDao().insertmemes(result.body()!!.data.memes)
                memesLiveData.postValue(result.body())
            }

        }
        else{
            val memes=memeDatabase.memeDao().getMemes()
            val memeList=Jokes(Data(memes),true)
            memesLiveData.postValue(memeList)
        }


    }
    suspend fun deleteMeme(meme : Meme){
        memeDatabase.memeDao().delete(meme)
    }
}
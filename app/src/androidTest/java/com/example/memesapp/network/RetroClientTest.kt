package com.example.memesapp.network

import com.example.memesapp.api.ApiUtility
import org.junit.Test
import retrofit2.Retrofit

class RetroClientTest {
    @Test
    fun testRetrofitInstance(){
        val instance: Retrofit = RetrofitClient().retrofit
        assert(instance.baseUrl().toString() == ApiUtility.BASE_URL)
    }
    @Test
    fun testMemesService(){
        val service =MemeService(RetrofitClient().retrofit)
        val response = service.getMemes().execute()
        val errorBody = response.errorBody()
        assert(errorBody == null)

        //Check for success body
        val responseWrapper = response.body()
        assert(responseWrapper != null)
        assert(response.code() == 200)
    }
}


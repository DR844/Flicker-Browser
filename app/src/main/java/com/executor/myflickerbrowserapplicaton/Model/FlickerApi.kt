package com.executor.myflickerbrowserapplicaton.Model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://api.flickr.com"

interface FlickerApi {
    @GET("/services/feeds/photos_public.gne?tags=android,oreo&format=json&nojsoncallback=1")
    fun getData(): Call<FlickerListModel>
}

object MyServices {
    var myInstance: FlickerApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        myInstance = retrofit.create(FlickerApi::class.java)
    }
}
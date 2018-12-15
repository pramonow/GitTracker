package com.pramonow.gittracker

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkBuilder {

    lateinit var service:GitEndpoit

    fun initializeNetwork(){
        var retrofit = Retrofit.Builder().baseUrl("www")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(GitEndpoit::class.java)
    }


}
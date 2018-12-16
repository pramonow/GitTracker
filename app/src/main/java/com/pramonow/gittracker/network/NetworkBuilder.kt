package com.pramonow.gittracker.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkBuilder {

    lateinit var service: GitEndpoit

    fun initializeNetwork(){
        var retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(GitEndpoit::class.java)
    }


}
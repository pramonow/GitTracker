package com.pramonow.gittracker.network

import com.pramonow.gittracker.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
    Network builder to initialize Retrofit
 */

object NetworkBuilder {

    lateinit var service: GitEndpoit

    fun initializeNetwork(){
        var retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(GitEndpoit::class.java)
    }


}
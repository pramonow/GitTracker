package com.pramonow.gittracker.network

import com.pramonow.gittracker.model.RepoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitEndpoit {

    @GET("users/{username}/repos")
    fun getRepoList(@Path("username") username:String):Call<List<RepoModel>>

}
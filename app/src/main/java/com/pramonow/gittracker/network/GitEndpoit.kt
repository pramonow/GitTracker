package com.pramonow.gittracker.network

import com.pramonow.gittracker.model.RepoDetail
import com.pramonow.gittracker.model.RepoModel
import com.pramonow.gittracker.model.User
import com.pramonow.gittracker.model.UserDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitEndpoit {

    @GET("users/{username}/repos")
    fun getRepoList(@Path("username") username:String,@Query("per_page") limit:Int, @Query("page") page:Int):Call<List<RepoModel>>
    @GET("users/{username}")
    fun getUser(@Path("username") username:String):Call<UserDetail>
    @GET("repos/{username}/{reponame}")
    fun getRepo(@Path("username") username:String, @Path("reponame") reponame:String):Call<RepoDetail>
    @GET("search/users")
    fun getUserList(@Path("q") username:String, @Path("per_page") limit: Int, @Query("page") page: Int):Call<List<User>>


}
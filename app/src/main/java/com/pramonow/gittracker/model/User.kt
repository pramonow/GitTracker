package com.pramonow.gittracker.model

import com.google.gson.annotations.SerializedName
import java.sql.Time

data class User(
    @SerializedName("login")
    var userName:String = "",
    @SerializedName("name")
    var name:String = "",
    @SerializedName("id")
    var id:Long = 0,
    @SerializedName("avatar_url")
    var avatarUrl:String = "",
    @SerializedName("public_repos")
    var publicRepoCount:Int = 0,
    @SerializedName("created_at")
    var createdTime:Time,
    @SerializedName("updated_at")
    var updatedTime:Time
)
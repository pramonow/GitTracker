package com.pramonow.gittracker.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    var userName:String = "",
    @SerializedName("id")
    var id:Long = 0,
    @SerializedName("avatar_url")
    var avatarUrl:String? = ""
)
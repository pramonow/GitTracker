package com.pramonow.gittracker.model

import com.google.gson.annotations.SerializedName

data class RepoModel(

    @SerializedName("id")
    var id:Long = 0,

    @SerializedName("name")
    var name:String = ""

)
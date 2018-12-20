package com.pramonow.gittracker.model

import com.google.gson.annotations.SerializedName
import java.sql.Time

data class RepoModel(

    @SerializedName("id")
    var id:Long = 0,

    @SerializedName("name")
    var name:String = "",

    @SerializedName("description")
    var description:String,

    @SerializedName("html_url")
    var webUrl:String = "",

    @SerializedName("created_time")
    var createdTime:String,

    @SerializedName("updated_at")
    var updatedTime:String,

    @SerializedName("license")
    var license: License

)
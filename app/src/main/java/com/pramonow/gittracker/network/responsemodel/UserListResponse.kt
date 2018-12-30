package com.pramonow.gittracker.network.responsemodel

import com.google.gson.annotations.SerializedName
import com.pramonow.gittracker.model.User

data class UserListResponse(

    @SerializedName("total_count")
    var totalItem:Int = 0,

    @SerializedName("incomplete_result")
    var incompleteResult:Boolean = false,

    @SerializedName("items")
    var userList:List<User>

)
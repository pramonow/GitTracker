package com.pramonow.gittracker.contract

import com.pramonow.gittracker.model.UserDetail

interface InputUserContract {

    interface Activity{
        fun showLoading(show:Boolean)
        fun showToast(message:String)
        fun showToast(message:Int)
        fun navigateToUserActivity(userDetail:UserDetail)
        fun navigateToUserListActivity(username: String)
    }

    interface Presenter{
        fun fetchUser(username:String)
    }
}
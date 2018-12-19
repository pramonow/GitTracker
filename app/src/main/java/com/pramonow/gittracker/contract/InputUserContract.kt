package com.pramonow.gittracker.contract

import com.pramonow.gittracker.model.User

interface InputUserContract {

    interface Activity{
        fun showLoading(show:Boolean)
        fun showToast(message:String)
        fun navigateToUserActivity(user:User)
    }

    interface Presenter{
        fun fetchUser(username:String)
    }
}
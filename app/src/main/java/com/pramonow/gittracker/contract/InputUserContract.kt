package com.pramonow.gittracker.contract

interface InputUserContract {

    interface Activity{
        fun showLoading(show:Boolean)
    }

    interface Presenter{
        fun fetchUser(username:String)
    }
}
package com.pramonow.gittracker.contract

import com.pramonow.gittracker.model.UserDetail

interface UserProfileContract {

    interface Activity{
        fun showLoading(show:Boolean)
        fun navigateToRepositoryScreen(userDetail:UserDetail)
    }

    interface Presenter{
    }
}
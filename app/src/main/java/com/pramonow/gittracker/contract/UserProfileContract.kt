package com.pramonow.gittracker.contract

import com.pramonow.gittracker.model.User

interface UserProfileContract {

    interface Activity{
        fun showLoading(show:Boolean)
        fun navigateToRepositoryScreen(user:User)
    }

    interface Presenter{
    }
}
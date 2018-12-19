package com.pramonow.gittracker.contract

interface UserProfileContract {

    interface Activity{
        fun showLoading(show:Boolean)
        fun navigateToRepositoryScreen()
    }

    interface Presenter{
    }
}
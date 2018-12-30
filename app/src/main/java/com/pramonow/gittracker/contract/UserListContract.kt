package com.pramonow.gittracker.contract

import com.pramonow.gittracker.model.User
import com.pramonow.gittracker.model.UserDetail

interface UserListContract {

    interface Activity{
        fun showUserList(repoList:List<User>)
        fun showLoading(boolean: Boolean)
        fun showToast(message:String)
        fun showToast(message:Int)
        fun navigateToUserDetail(userDetail: UserDetail)
    }

    interface Presenter{
        fun getUserList(username:String, limit:Int, page:Int)
        fun fetchUserDetail(username: String)
    }
}
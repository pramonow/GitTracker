package com.pramonow.gittracker.presenter

import android.util.Log
import com.pramonow.gittracker.R
import com.pramonow.gittracker.contract.UserListContract
import com.pramonow.gittracker.model.User
import com.pramonow.gittracker.model.UserDetail
import com.pramonow.gittracker.network.NetworkBuilder
import com.pramonow.gittracker.network.responsemodel.UserListResponse
import com.pramonow.gittracker.util.SUCCESS_RESPONE_CODE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListPresenter:UserListContract.Presenter{

    private var activity:UserListContract.Activity

    constructor(activity:UserListContract.Activity){
        this.activity = activity
    }

    /*
        API calling block
     */
    override fun getUserList(username: String, limit: Int, page: Int) {
        val call = NetworkBuilder.service.getUserList(username,limit,page)

        call.enqueue(object : Callback<UserListResponse> {
            override fun onResponse(call: Call<UserListResponse>, response: Response<UserListResponse>) {


                if(response.code() == 403)
                {
                    //give error message for too much hit
                }

                var result = response.body()

                if (result != null) {
                    if(result.totalItem == 0)
                        activity.showToast(R.string.no_user_found)
                    else
                        activity.showUserList(result.userList)
                }

                activity.showLoading(false)
            }

            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                activity.showToast(R.string.network_error)
                activity.showLoading(false)
            }
        })
    }

    override fun fetchUserDetail(username: String)
    {
        activity.showLoading(true)

        val call = NetworkBuilder.service.getUser(username)

        //API calling block
        call.enqueue(object : Callback<UserDetail> {
            override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                activity.showLoading(false)

                if(response.code() == SUCCESS_RESPONE_CODE) {
                    var result = response.body()

                    if (result != null)
                        activity.navigateToUserDetail(result)
                }
                else
                {
                    //Might need to check further, 404 will result in userDetail not found
                    activity.showToast(R.string.not_found_message)
                }
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                activity.showLoading(false)
                activity.showToast(R.string.network_error)
            }
        })
    }

}
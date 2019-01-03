package com.pramonow.gittracker.presenter

import com.pramonow.gittracker.R
import com.pramonow.gittracker.contract.InputUserContract
import com.pramonow.gittracker.network.NetworkBuilder
import com.pramonow.gittracker.network.responsemodel.UserListResponse
import com.pramonow.gittracker.util.CANCELED_EXCEPTION
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputUserPresenter(private var activity: InputUserContract.Activity) :InputUserContract.Presenter{

    private lateinit var call:Call<UserListResponse>

    override fun fetchUserList(username: String) {
        activity.showLoading(true)

        //hardcoded for now, resulting in 40 query result
        call = NetworkBuilder.service.getUserList(username,40,1)

        call.enqueue(object : Callback<UserListResponse> {
            override fun onResponse(call: Call<UserListResponse>, response: Response<UserListResponse>) {

                if(response.code() == 403) {
                    //give error message for too much hit
                    activity.showToast(R.string.too_much_attempt)
                    activity.showLoading(false)
                    return
                }

                val result = response.body()

                if (result != null) {
                    if(result.totalItem == 0)
                        activity.showToast(R.string.no_user_found)
                    else
                        activity.navigateToUserListActivity(result.userList, username)
                }

                activity.showLoading(false)
            }

            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                if(!t.message.equals(CANCELED_EXCEPTION)) {
                    activity.showLoading(false)
                    activity.showToast(R.string.network_error)
                }
            }
        })
    }

    override fun cancelRetrofitCall() {
        if(::call.isInitialized)
            call.cancel()
    }

}
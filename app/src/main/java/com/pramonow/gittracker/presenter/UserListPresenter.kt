package com.pramonow.gittracker.presenter

import com.pramonow.gittracker.R
import com.pramonow.gittracker.contract.UserListContract
import com.pramonow.gittracker.model.UserDetail
import com.pramonow.gittracker.network.NetworkBuilder
import com.pramonow.gittracker.util.CANCELED_EXCEPTION
import com.pramonow.gittracker.util.SUCCESS_RESPONE_CODE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListPresenter(private var activity: UserListContract.Activity) :UserListContract.Presenter{

    private lateinit var call:Call<UserDetail>

    override fun fetchUserDetail(username: String) {
        activity.showLoading(true)

        call = NetworkBuilder.service.getUser(username)

        //API calling block
        call.enqueue(object : Callback<UserDetail> {
            override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                activity.showLoading(false)

                if(response.code() == SUCCESS_RESPONE_CODE) {
                    val result = response.body()

                    if (result != null)
                        activity.navigateToUserDetail(result)
                }
                else {
                    //code 404 for user not found
                    activity.showToast(R.string.not_found_message)
                }
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
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
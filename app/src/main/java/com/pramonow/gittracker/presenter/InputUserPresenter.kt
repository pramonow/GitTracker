package com.pramonow.gittracker.presenter

import com.pramonow.gittracker.R
import com.pramonow.gittracker.contract.InputUserContract
import com.pramonow.gittracker.model.UserDetail
import com.pramonow.gittracker.network.NetworkBuilder
import com.pramonow.gittracker.util.SUCCESS_RESPONE_CODE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputUserPresenter:InputUserContract.Presenter{

    private var activity:InputUserContract.Activity

    constructor(activity:InputUserContract.Activity)
    {
        this.activity = activity
    }

    override fun fetchUser(username: String)
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
                        activity.navigateToUserActivity(result)
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
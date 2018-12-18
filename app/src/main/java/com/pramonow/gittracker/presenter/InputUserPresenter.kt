package com.pramonow.gittracker.presenter

import android.util.Log
import com.pramonow.gittracker.contract.InputUserContract
import com.pramonow.gittracker.model.User
import com.pramonow.gittracker.network.NetworkBuilder
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

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                activity.showLoading(false)

                if(response.code() == 200) {
                    var result = response.body()
                    Log.d("baniman", "success " + result)
                }
                else
                {
                    Log.d("baniman", "not 200 " + response)
                }

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                activity.showLoading(false)
                Log.d("baniman", "fail " + t.message)
            }
        })


    }

}
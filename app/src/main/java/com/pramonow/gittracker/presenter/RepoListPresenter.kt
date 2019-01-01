package com.pramonow.gittracker.presenter

import android.content.res.Resources
import android.util.Log
import com.pramonow.gittracker.R
import com.pramonow.gittracker.contract.RepoListContract
import com.pramonow.gittracker.model.RepoModel
import com.pramonow.gittracker.network.NetworkBuilder
import com.pramonow.gittracker.util.CANCELED_EXCEPTION
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoListPresenter:RepoListContract.Presenter{

    private var activity:RepoListContract.Activity
    private lateinit var call:Call<List<RepoModel>>

    constructor(activity:RepoListContract.Activity){
        this.activity = activity
    }

    /*
        API calling block
     */
    override fun getRepoList(username: String, limit: Int, page: Int) {
        call = NetworkBuilder.service.getRepoList(username,limit,page)

        call.enqueue(object : Callback<List<RepoModel>> {
            override fun onResponse(call: Call<List<RepoModel>>, response: Response<List<RepoModel>>) {

                var result = response.body()

                if (result != null) {
                    activity.showRepoList(result)
                }
            }

            override fun onFailure(call: Call<List<RepoModel>>, t: Throwable) {
                if(!t.message.equals(CANCELED_EXCEPTION)) {
                    activity.setLoading(false)
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
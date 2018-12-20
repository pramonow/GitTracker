package com.pramonow.gittracker.presenter

import android.util.Log
import com.pramonow.gittracker.contract.RepoListContract
import com.pramonow.gittracker.model.RepoModel
import com.pramonow.gittracker.network.NetworkBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoListPresenter:RepoListContract.Presenter{

    private var activity:RepoListContract.Activity

    constructor(activity:RepoListContract.Activity){
        this.activity = activity
    }

    override fun getRepoList(username: String, limit: Int, page: Int) {
        val call = NetworkBuilder.service.getRepoList(username,limit,page)

        call.enqueue(object : Callback<List<RepoModel>> {
            override fun onResponse(call: Call<List<RepoModel>>, response: Response<List<RepoModel>>) {
                //Success

                var result = response.body()
                Log.d("baniman", "res " + response.body())

                if (result != null) {
                    activity.showRepoList(result)
                }
            }

            override fun onFailure(call: Call<List<RepoModel>>, t: Throwable) {
                //Fail
                Log.d("baniman", "fail " + t.message)
            }
        })
    }

}
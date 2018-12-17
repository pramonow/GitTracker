package com.pramonow.gittracker.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.pramonow.gittracker.R
import com.pramonow.gittracker.adapter.RepoAdapter
import com.pramonow.gittracker.model.RepoModel
import com.pramonow.gittracker.network.NetworkBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var repoRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repoRecyclerView = findViewById(R.id.list)
        repoRecyclerView.layoutManager = LinearLayoutManager(this)

        var repoAdapter = RepoAdapter()
        repoRecyclerView.adapter = repoAdapter

        val call = NetworkBuilder.service.getRepoList("pramonow",10,1)

        call.enqueue(object : Callback<List<RepoModel>> {
            override fun onResponse(call: Call<List<RepoModel>>, response: Response<List<RepoModel>>) {
                //Success

                var result = response.body()

                Log.d("baniman", "success " + response)

                repoAdapter.repoList = result as MutableList<RepoModel>
                repoAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<RepoModel>>, t: Throwable) {
                Log.d("baniman", "fail " + t.message)
                //Fail
            }
        })

    }
}

package com.pramonow.gittracker.activity

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.pramonow.endlessrecyclerview.EndlessRecyclerView
import com.pramonow.endlessrecyclerview.EndlessScrollCallback
import com.pramonow.gittracker.R
import com.pramonow.gittracker.adapter.RepoAdapter
import com.pramonow.gittracker.contract.RepoListContract
import com.pramonow.gittracker.model.RepoModel
import com.pramonow.gittracker.presenter.RepoListPresenter

class RepoListActivity : AppCompatActivity(), EndlessScrollCallback, RepoListContract.Activity {

    val repoListPresenter = RepoListPresenter(this)

    lateinit var repoRecyclerView: EndlessRecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var repoAdapter: RepoAdapter
    var isLoadingData = false
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)

        repoRecyclerView = findViewById(R.id.endless_repo_list)

        repoAdapter = RepoAdapter()
        repoRecyclerView.recyclerView.adapter = repoAdapter

        //Set callback for loading more
        repoRecyclerView.setEndlessScrollCallback(this)
        repoRecyclerView.setLoadBeforeBottom(true)

        repoListPresenter.getRepoList("pramonow",10, page)
    }

    override fun loadMore() {

        repoRecyclerView.blockLoading()
        repoListPresenter.getRepoList("pramonow",10, page)
        Log.d("baniman", "load")

    }

    override fun showRepoList(repoList: List<RepoModel>) {

        if(repoList.size < 10)
            repoRecyclerView.setLastPage()

        repoAdapter.addRepoList(repoList)
        repoRecyclerView.releaseBlock()
        page++
    }

    override fun setLoading(boolean: Boolean) {
        isLoadingData = boolean
    }

}
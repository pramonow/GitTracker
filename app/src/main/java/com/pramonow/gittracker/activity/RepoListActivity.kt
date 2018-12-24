package com.pramonow.gittracker.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.pramonow.endlessrecyclerview.EndlessRecyclerView
import com.pramonow.endlessrecyclerview.EndlessScrollCallback
import com.pramonow.gittracker.R
import com.pramonow.gittracker.adapter.RepoAdapter
import com.pramonow.gittracker.contract.RepoListContract
import com.pramonow.gittracker.model.RepoModel
import com.pramonow.gittracker.model.User
import com.pramonow.gittracker.presenter.RepoListPresenter
import com.pramonow.gittracker.util.AdapterOnClick

class RepoListActivity : AppCompatActivity(), EndlessScrollCallback, RepoListContract.Activity, AdapterOnClick {

    override fun onClick(url: String, repoName:String) {
        var intent = Intent(this,RepoDetailActivity::class.java)
        intent.putExtra("url",url)
        intent.putExtra("reponame", repoName)
        startActivity(intent)
    }

    val repoListPresenter = RepoListPresenter(this)

    lateinit var repoRecyclerView: EndlessRecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var repoAdapter: RepoAdapter
    var isLoadingData = false
    var page = 1
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)

        repoRecyclerView = findViewById(R.id.endless_repo_list)
        user = intent.extras.getParcelable<User>("user")


        repoAdapter = RepoAdapter(this)
        repoRecyclerView.recyclerView.adapter = repoAdapter

        //Set callback for loading more
        repoRecyclerView.setEndlessScrollCallback(this)
        repoRecyclerView.setLoadBeforeBottom(true)

        repoListPresenter.getRepoList(user.userName,10, page)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setTitle(user.userName + " repository")

    }

    override fun loadMore() {

        repoRecyclerView.blockLoading()
        repoListPresenter.getRepoList(user.name,10, page)
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

    // create an action bar button
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuItem = menuInflater.inflate(R.menu.action_bar_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    // handle button activities
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.terms -> {
                startActivity(Intent(this,LicenseActivity::class.java))
                return true
            }
            R.id.about -> {
                startActivity(Intent(this,AboutActivity::class.java))
                return true
            }
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
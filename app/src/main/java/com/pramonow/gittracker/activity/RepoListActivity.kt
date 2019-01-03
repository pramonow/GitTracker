package com.pramonow.gittracker.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.pramonow.endlessrecyclerview.EndlessRecyclerView
import com.pramonow.endlessrecyclerview.EndlessScrollCallback
import com.pramonow.gittracker.R
import com.pramonow.gittracker.adapter.RepoAdapter
import com.pramonow.gittracker.contract.RepoListContract
import com.pramonow.gittracker.model.RepoModel
import com.pramonow.gittracker.model.UserDetail
import com.pramonow.gittracker.presenter.RepoListPresenter
import com.pramonow.gittracker.util.AdapterOnClick
import com.pramonow.gittracker.util.REPO_NAME_INTENT
import com.pramonow.gittracker.util.URL_INTENT
import com.pramonow.gittracker.util.USER_INTENT

class RepoListActivity : AppCompatActivity(), EndlessScrollCallback, RepoListContract.Activity, AdapterOnClick {

    val repoListPresenter = RepoListPresenter(this)

    lateinit var repoRecyclerView: EndlessRecyclerView
    lateinit var repoAdapter: RepoAdapter
    lateinit var userDetail: UserDetail

    var isLoadingData = false
    var defaultLimitPage = 10
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)

        userDetail = intent.extras.getParcelable<UserDetail>(USER_INTENT)

        repoAdapter = RepoAdapter(this)

        //Setting endless recycler view block
        repoRecyclerView = findViewById(R.id.endless_repo_list)
        repoRecyclerView.loadOffset = 3
        repoRecyclerView.setLoadBeforeBottom(true)
        repoRecyclerView.setEndlessScrollCallback(this)
        repoRecyclerView.adapter = repoAdapter

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setTitle(userDetail.userName + "'s repository")

        repoListPresenter.getRepoList(userDetail.userName,defaultLimitPage, page)
    }

    override fun onBackPressed() {
        repoListPresenter.cancelRetrofitCall()
        super.onBackPressed()
    }

    // create an action bar button
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuItem = menuInflater.inflate(R.menu.action_bar_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    // handle button activities
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
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

    //Function to be implemented for adapter click interface
    override fun onClick(url: String, repoName:String) {
        val intent = Intent(this,RepoDetailActivity::class.java)
        intent.putExtra(URL_INTENT,url)
        intent.putExtra(REPO_NAME_INTENT, repoName)
        startActivity(intent)
    }

    //Function to be implemented for endless scroll view callback
    override fun loadMore() {
        //block new data from loading until the new data finish being fetched
        repoRecyclerView.blockLoading()
        repoListPresenter.getRepoList(userDetail.userName,defaultLimitPage, page)
    }

    /*
        Contract functions Implementation block
     */
    override fun showRepoList(repoList: List<RepoModel>) {

        //If repos obtained from web is less than 10 means it is the last page
        //Setting last page will make endless view from loading new data
        if(repoList.size < defaultLimitPage)
            repoRecyclerView.setLastPage()

        repoAdapter.addRepoList(repoList)

        //enable loading again
        repoRecyclerView.releaseBlock()
        page++
    }

    override fun setLoading(boolean: Boolean) {
        isLoadingData = boolean

        if(boolean == true)
            repoRecyclerView.blockLoading()
        else
            repoRecyclerView.releaseBlock()
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(message: Int) {
        Toast.makeText(this,getString(message),Toast.LENGTH_SHORT).show()
    }

}
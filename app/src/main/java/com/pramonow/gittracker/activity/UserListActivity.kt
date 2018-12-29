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
import com.pramonow.gittracker.adapter.UserAdapter
import com.pramonow.gittracker.contract.UserListContract
import com.pramonow.gittracker.model.User
import com.pramonow.gittracker.model.UserDetail
import com.pramonow.gittracker.presenter.UserListPresenter
import com.pramonow.gittracker.util.AdapterOnClick
import com.pramonow.gittracker.util.USER_INTENT

class UserListActivity : AppCompatActivity(), EndlessScrollCallback, UserListContract.Activity, AdapterOnClick {

    val userListPresenter = UserListPresenter(this)

    lateinit var userRecyclerView: EndlessRecyclerView
    lateinit var userAdapter: UserAdapter
    lateinit var userName: String

    var isLoadingData = false
    var defaultLimitPage = 10
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        userName = "praacaccst"

        userAdapter = UserAdapter(this)

        //Setting endless recycler view block
        userRecyclerView = findViewById(R.id.endless_repo_list)
        userRecyclerView.loadOffset = 3
        userRecyclerView.setLoadBeforeBottom(true)
        userRecyclerView.setEndlessScrollCallback(this)
        userRecyclerView.adapter = userAdapter

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        userListPresenter.getUserList(userName,defaultLimitPage, page)
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
    override fun onClick(userName: String, extraParam:String) {
        userListPresenter.fetchUserDetail(userName);
    }

    //Function to be implemented for endless scroll view callback
    override fun loadMore() {
        //block new data from loading until the new data finish being fetched
        userRecyclerView.blockLoading()
        userListPresenter.getUserList(userName,defaultLimitPage, page)
    }

    /*
        Contract functions Implementation block
     */
    override fun navigateToUserDetail(userDetail: UserDetail) {
        var intent = Intent(this,UserProfileActivity::class.java)
        intent.putExtra(USER_INTENT,userDetail)
        startActivity(intent)
    }

    override fun showUserList(repoList: List<User>) {

        //If repos obtained from web is less than 10 means it is the last page
        //Setting last page will make endless view from loading new data
        if(repoList.size < defaultLimitPage)
            userRecyclerView.setLastPage()

        userAdapter.addUserList(repoList)

        //enable loading again
        userRecyclerView.releaseBlock()
        page++
    }

    override fun setLoading(boolean: Boolean) {
        isLoadingData = boolean

        if(boolean == true)
            userRecyclerView.blockLoading()
        else
            userRecyclerView.releaseBlock()
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(message: Int) {
        Toast.makeText(this,getString(message),Toast.LENGTH_SHORT).show()
    }

}
package com.pramonow.gittracker.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.pramonow.gittracker.R
import com.pramonow.gittracker.adapter.UserAdapter
import com.pramonow.gittracker.contract.UserListContract
import com.pramonow.gittracker.model.User
import com.pramonow.gittracker.model.UserDetail
import com.pramonow.gittracker.presenter.UserListPresenter
import com.pramonow.gittracker.util.AdapterOnClick
import com.pramonow.gittracker.util.USERLIST_INTENT
import com.pramonow.gittracker.util.USERNAME_INTENT
import com.pramonow.gittracker.util.USER_INTENT

class UserListActivity : AppCompatActivity(), UserListContract.Activity, AdapterOnClick {

    val userListPresenter = UserListPresenter(this)

    lateinit var userRecyclerView: RecyclerView
    lateinit var userAdapter: UserAdapter
    lateinit var userList: List<User>
    lateinit var userName: String
    lateinit var loadingLayout:FrameLayout
    lateinit var queryText:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        loadingLayout = findViewById(R.id.loading_layout)
        queryText = findViewById(R.id.top_text)

        userList = intent.getParcelableArrayListExtra(USERLIST_INTENT)
        userName = intent.getStringExtra(USERNAME_INTENT)

        queryText.text = "Search results on $userName"

        userAdapter = UserAdapter(this)

        //Setting recycler view block
        //Not using endless recycler view since max hit is 10/minutes
        userRecyclerView = findViewById(R.id.user_list)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = userAdapter
        userAdapter.addUserList(userList)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        userListPresenter.cancelRetrofitCall()
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
    override fun onClick(userName: String, extraParam:String) {
        userListPresenter.fetchUserDetail(userName)
    }

    /*
        Contract functions Implementation block
     */
    override fun navigateToUserDetail(userDetail: UserDetail) {
        val intent = Intent(this,UserProfileActivity::class.java)
        intent.putExtra(USER_INTENT,userDetail)
        startActivity(intent)
    }

    override fun showLoading(boolean: Boolean) {
        if(boolean == true)
            loadingLayout.visibility = View.VISIBLE
        else
            loadingLayout.visibility = View.INVISIBLE
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(message: Int) {
        Toast.makeText(this,getString(message),Toast.LENGTH_SHORT).show()
    }

}
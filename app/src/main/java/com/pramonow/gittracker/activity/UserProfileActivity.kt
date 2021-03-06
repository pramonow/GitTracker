package com.pramonow.gittracker.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.pramonow.gittracker.R
import com.pramonow.gittracker.contract.UserProfileContract
import com.pramonow.gittracker.model.UserDetail
import com.pramonow.gittracker.presenter.UserProfilePresenter
import com.pramonow.gittracker.util.USER_INTENT
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

class UserProfileActivity : AppCompatActivity(), UserProfileContract.Activity {

    val userProfilePresenter = UserProfilePresenter(this)

    lateinit var avatarImage:ImageView
    lateinit var nameText:TextView
    lateinit var userNameText:TextView
    lateinit var repoCountTextView: TextView
    lateinit var locationTextView:TextView
    lateinit var biographyTextView: TextView

    lateinit var showRepoButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        var user = intent.extras.getParcelable<UserDetail>(USER_INTENT)

        avatarImage = findViewById(R.id.avatar_image)
        nameText = findViewById(R.id.name_text)
        userNameText = findViewById(R.id.username_text)
        repoCountTextView = findViewById(R.id.repos_count)
        showRepoButton = findViewById(R.id.show_button)
        locationTextView = findViewById(R.id.location_text)
        biographyTextView = findViewById(R.id.bio_text)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initializeUserProfile(user)
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


    // method for view initialization block
    private fun initializeUserProfile(userDetail:UserDetail) {
        Picasso.get().load(userDetail.avatarUrl).placeholder(R.color.loading_block_gray).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(avatarImage)
        nameText.setText(userDetail.name)
        userNameText.setText(userDetail.userName)
        repoCountTextView.setText("Public repos: " + userDetail.publicRepoCount)
        showRepoButton.setOnClickListener { v -> navigateToRepositoryScreen(userDetail) }
        setTitle(userDetail.userName + "'s profile")

        if(!userDetail.biography.isNullOrEmpty())
            biographyTextView.setText(userDetail.biography)
        if(!userDetail.location.isNullOrEmpty())
            locationTextView.setText(userDetail.location)
    }


    /*
        Contract functions Implementation
     */

    override fun showLoading(show: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToRepositoryScreen(userDetail:UserDetail) {
        var intent = Intent(this,RepoListActivity::class.java)
        intent.putExtra(USER_INTENT,userDetail)
        startActivity(intent)
    }


}
package com.pramonow.gittracker.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.pramonow.gittracker.R
import com.pramonow.gittracker.contract.UserProfileContract
import com.pramonow.gittracker.model.User
import com.pramonow.gittracker.presenter.UserProfilePresenter
import com.squareup.picasso.Picasso

class UserProfileActivity : AppCompatActivity(), UserProfileContract.Activity {

    val userProfilePresenter = UserProfilePresenter(this)

    lateinit var avatarImage:ImageView
    lateinit var nameText:TextView
    lateinit var userNameText:TextView
    lateinit var repoCountTextView: TextView
    lateinit var showRepoButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        var user = intent.extras.getParcelable<User>("user")

        avatarImage = findViewById(R.id.avatar_image)
        nameText = findViewById(R.id.name_text)
        userNameText = findViewById(R.id.username_text)
        repoCountTextView = findViewById(R.id.repos_count)
        showRepoButton = findViewById(R.id.show_button)

        Picasso.get().load(user.avatarUrl).into(avatarImage)
        nameText.setText(user.name)
        userNameText.setText(user.userName)
        repoCountTextView.setText("Public repos: " + user.publicRepoCount)
        showRepoButton.setOnClickListener { v -> startActivity(Intent(this,RepoListActivity::class.java)) }

    }

    override fun showLoading(show: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToRepositoryScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
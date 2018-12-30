package com.pramonow.gittracker.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.pramonow.gittracker.R
import com.pramonow.gittracker.contract.InputUserContract
import com.pramonow.gittracker.model.UserDetail
import com.pramonow.gittracker.presenter.InputUserPresenter
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.pramonow.gittracker.util.USERNAME_INTENT
import com.pramonow.gittracker.util.USER_INTENT

class InputUserActivity : AppCompatActivity(), InputUserContract.Activity {

    val inputUserPresenter:InputUserContract.Presenter = InputUserPresenter(this)

    lateinit var inputText:EditText
    lateinit var confirmButton: Button
    lateinit var loadingLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_user)

        inputText = findViewById(R.id.input_edit_text)
        confirmButton = findViewById(R.id.confirm_button)
        loadingLayout = findViewById(R.id.loading_layout)

        inputText.highlightColor = resources.getColor(R.color.colorLightGray)

        confirmButton.setOnClickListener { v -> inputUserPresenter.fetchUser(inputText.text.toString()) }

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
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /*
        Contract Functions block
     */

    override fun showLoading(show: Boolean) {
        if(show)
            loadingLayout.visibility = View.VISIBLE
        else
            loadingLayout.visibility = View.INVISIBLE
    }

    override fun navigateToUserActivity(userDetail: UserDetail) {
        var intent = Intent(this,UserProfileActivity::class.java)
        intent.putExtra(USER_INTENT,userDetail)
        startActivity(intent)
    }

    override fun navigateToUserListActivity(username: String) {
        var intent = Intent(this,UserListActivity::class.java)
        intent.putExtra(USERNAME_INTENT,username)
        startActivity(intent)
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun showToast(message: Int) {
        Toast.makeText(this,getString(message),Toast.LENGTH_SHORT).show()
    }



}
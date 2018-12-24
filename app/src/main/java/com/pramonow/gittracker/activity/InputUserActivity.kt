package com.pramonow.gittracker.activity

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.pramonow.gittracker.R
import com.pramonow.gittracker.contract.InputUserContract
import com.pramonow.gittracker.model.User
import com.pramonow.gittracker.presenter.InputUserPresenter
import android.view.Menu
import android.view.MenuItem
import android.widget.*

class InputUserActivity : AppCompatActivity(), InputUserContract.Activity {

    val inputUserPresenter:InputUserContract.Presenter = InputUserPresenter(this)

    lateinit var inputText:EditText
    lateinit var confirmButton: Button
    lateinit var loadingLayout: FrameLayout


    override fun showLoading(show: Boolean) {
        if(show)
            loadingLayout.visibility = View.VISIBLE
        else
            loadingLayout.visibility = View.INVISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_user)

        inputText = findViewById(R.id.input_edit_text)
        confirmButton = findViewById(R.id.confirm_button)
        loadingLayout = findViewById(R.id.loading_layout)

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

    override fun navigateToUserActivity(user: User) {

        var intent = Intent(this,UserProfileActivity::class.java)
        intent.putExtra("user",user)
        startActivity(intent)
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }



}
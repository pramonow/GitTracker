package com.pramonow.gittracker.activity

import android.content.Context
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
import com.pramonow.gittracker.model.User
import com.pramonow.gittracker.util.USERLIST_INTENT
import com.pramonow.gittracker.util.USERNAME_INTENT
import com.pramonow.gittracker.util.USER_INTENT
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.EditText

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
        confirmButton.setOnClickListener { v -> hideFocus(v); inputUserPresenter.fetchUserList(inputText.text.toString()) }

        inputText.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                confirmButton.performClick()
                return@OnEditorActionListener true
            }
            false
        })
    }

    override fun onBackPressed() {
        inputUserPresenter.cancelRetrofitCall()
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
            else -> return super.onOptionsItemSelected(item)
        }
    }

    // handle keyboard and focus hiding when user is querying
    private fun hideFocus(view:View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0)
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
        val intent = Intent(this,UserProfileActivity::class.java)
        intent.putExtra(USER_INTENT,userDetail)
        startActivity(intent)
    }

    override fun navigateToUserListActivity(userList: List<User>, username:String) {
        val intent = Intent(this,UserListActivity::class.java)
        intent.putExtra(USERLIST_INTENT,userList as ArrayList<User>)
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
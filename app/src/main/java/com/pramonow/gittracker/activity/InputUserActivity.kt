package com.pramonow.gittracker.activity

import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import com.pramonow.gittracker.R
import com.pramonow.gittracker.contract.InputUserContract
import com.pramonow.gittracker.presenter.InputUserPresenter

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

        confirmButton.setOnClickListener { v ->  inputUserPresenter.fetchUser(inputText.text.toString())}
    }
}
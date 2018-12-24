package com.pramonow.gittracker.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.pramonow.gittracker.R
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.TextView



class AboutActivity: AppCompatActivity(){

    lateinit var urlText:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_about)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setTitle("About")


        urlText = findViewById(R.id.project_link)
        urlText.isClickable = true
        urlText.movementMethod = LinkMovementMethod.getInstance()
        val text = "<a href='https://github.com/pramonow/GitTracker'> pramonow/GitTracker </a>"
        urlText.text = Html.fromHtml(text)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
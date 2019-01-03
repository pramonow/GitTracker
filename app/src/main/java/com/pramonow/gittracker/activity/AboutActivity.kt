package com.pramonow.gittracker.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.pramonow.gittracker.R
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.TextView
import com.pramonow.gittracker.util.REPO_URL_HREF

class AboutActivity: AppCompatActivity(){

    lateinit var urlText:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_about)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setTitle(getString(R.string.about))

        //Set Clickable link block
        urlText = findViewById(R.id.project_link)
        urlText.isClickable = true
        urlText.movementMethod = LinkMovementMethod.getInstance()
        val text = REPO_URL_HREF
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
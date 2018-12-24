package com.pramonow.gittracker.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.pramonow.gittracker.R
import android.webkit.WebViewClient
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.view.KeyEvent.KEYCODE_BACK




class RepoDetailActivity:AppCompatActivity(){

    lateinit var detailView:WebView
    lateinit var loadingLayout:FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent.getStringExtra("url")
        val repoName = intent.getStringExtra("reponame")

        setContentView(R.layout.activity_repo_detail)
        detailView = findViewById(R.id.web_view)
        loadingLayout = findViewById(R.id.loading_layout)
        detailView.settings.javaScriptEnabled = true

        detailView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

                if(url.contains(repoName)) {
                    loadingLayout.visibility = View.VISIBLE
                    return false
                }

                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(browserIntent)

                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                loadingLayout.visibility = View.INVISIBLE
            }
        }

        loadingLayout.visibility = View.VISIBLE
        detailView.loadUrl(url)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setTitle(repoName)

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

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.getAction() === KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (detailView.canGoBack()) {
                        detailView.goBack()
                    } else {
                        finish()
                    }
                    return true
                }
            }

        }
        return super.onKeyDown(keyCode, event)
    }

}
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


class RepoDetailActivity:AppCompatActivity(){

    lateinit var detailView:WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_repo_detail)
        detailView = findViewById(R.id.web_view)
        detailView.settings.javaScriptEnabled = true

        detailView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

                if(url.contains("github.com/pramonow"))
                return false

                var browserUrl:String = ""

                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    browserUrl = "http://" + url

                Log.d("baniman",url)

                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(browserIntent)

                return true
            }
        })

        detailView.loadUrl("https://github.com/pramonow/android-endlessrecyclerview")

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

}
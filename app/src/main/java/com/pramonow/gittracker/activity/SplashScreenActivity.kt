package com.pramonow.gittracker.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.pramonow.gittracker.R

class SplashScreenActivity: AppCompatActivity()
{
    //set delay time for splash screen to 1 second
    val delayTime = 1000L;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed( {
            /* Create an Intent that will start the Input User - Activity. */
            val mainIntent = Intent(this@SplashScreenActivity, InputUserActivity::class.java)
            this@SplashScreenActivity.startActivity(mainIntent)
            this@SplashScreenActivity.finish()
        }, delayTime)
    }
}
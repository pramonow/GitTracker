package com.pramonow.gittracker

import android.app.Application
import com.pramonow.gittracker.network.NetworkBuilder

class MainApplication:Application(){

    override fun onCreate() {
        super.onCreate()
        NetworkBuilder.initializeNetwork()
    }
}
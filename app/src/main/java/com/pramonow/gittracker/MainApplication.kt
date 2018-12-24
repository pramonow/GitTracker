package com.pramonow.gittracker

import android.app.Application
import com.pramonow.gittracker.network.NetworkBuilder

/*
    Main Application for the application
    Used to initialize network retrofit
 */
class MainApplication:Application(){

    override fun onCreate() {
        super.onCreate()
        NetworkBuilder.initializeNetwork()
    }
}
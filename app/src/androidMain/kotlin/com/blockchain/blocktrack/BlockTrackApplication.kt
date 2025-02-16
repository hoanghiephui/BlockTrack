package com.blockchain.blocktrack

import android.app.Application
import com.blockchain.blocktrack.di.AppComponent
import com.blockchain.blocktrack.di.create

class BlockTrackApplication : Application() {

    override fun onCreate() {
        appComponent = AppComponent.create(this)
        /*SingletonImageLoader.setSafe {
            //appComponent.provideImageLoader()
        }*/
        super.onCreate()
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}
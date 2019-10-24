package ru.sample.presentation

import android.app.Application
import android.util.Log
import io.reactivex.plugins.RxJavaPlugins

class AndroidApplication : Application() {

    companion object{
        lateinit var application: AndroidApplication
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        RxJavaPlugins.setErrorHandler {
            Log.d("RX", "error: " + it.message)
            it.printStackTrace()
        }
    }
}
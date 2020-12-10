package com.e.e_commerce

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.e.e_commerce.prefernce.AppPreferences

open class App: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        AppPreferences.init(this)
        MultiDex.install(this)

    }
    companion object {
        lateinit var context: Context

    }
}
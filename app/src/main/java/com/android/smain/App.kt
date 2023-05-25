package com.android.smain

import android.app.Application
import com.android.smain.repository.database.MyObjectBox
import io.objectbox.BoxStore

class App: Application() {

    companion object {
        var INSTANCE: App? = null
        var mBoxStore: BoxStore? = null
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        mBoxStore = MyObjectBox.builder().androidContext(this).build()
    }
}
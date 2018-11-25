package com.example.f5onz.applicationbdd

import android.app.Application
import com.example.f5onz.applicationbdd.CourseDbHelper.Companion.instance

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
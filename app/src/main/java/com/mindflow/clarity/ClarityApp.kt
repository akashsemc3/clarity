package com.mindflow.clarity

import android.app.Application
import com.mindflow.clarity.data.StreakPreferences

class ClarityApp : Application() {

    lateinit var preferences: StreakPreferences
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        preferences = StreakPreferences(this)
    }

    companion object {
        lateinit var instance: ClarityApp
            private set
    }
}

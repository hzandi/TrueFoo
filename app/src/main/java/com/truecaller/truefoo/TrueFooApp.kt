package com.truecaller.truefoo

import android.app.Application
import com.truecaller.data.LocalStorage
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class TrueFooApp: Application() {

    @Inject
    lateinit var localStorage: LocalStorage

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}



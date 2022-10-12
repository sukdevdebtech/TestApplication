package com.testapplication.application

import android.app.Application
import com.testapplication.di.initKodein

import org.kodein.di.KodeinAware

class App  : Application(), KodeinAware {
    override val kodein = initKodein(this)

 /*   override fun onCreate() {
        super.onCreate()

        //Shared Preference
        PreferenceUtility.init(this)
    }*/
}
package com.testapplication.di

import android.app.Application
import com.testapplication.di.modules.netModule
import com.testapplication.di.modules.repoModule
import com.testapplication.di.modules.viewModelModule

import org.kodein.di.Kodein
import org.kodein.di.android.x.androidXModule


fun initKodein(app: Application) =
    Kodein.lazy {
        import(androidXModule(app))
        import(netModule)
        import(repoModule)
        import(viewModelModule)
    }

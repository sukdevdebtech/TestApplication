package com.testapplication.di.modules


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.testapplication.BuildConfig
import com.testapplication.network.ApiInterface
import com.testapplication.network.NetworkConnectionInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.kodein.di.generic.with

val netModule = Kodein.Module(name = "netModule") {
    constant("baseUrl") with BuildConfig.BASE_URL
    this.bind() from this.singleton { NetworkConnectionInterceptor(this.instance()) }
    this.bind() from this.singleton { ApiInterface(this.instance()) }
    this.bind<Gson>() with this.singleton {
        GsonBuilder().create()
    }

}

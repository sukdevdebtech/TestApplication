package com.testapplication.network

import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.testapplication.network.ApiConstant.Companion.LIST
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ApiInterface {
 //   page=2&limit=20
    @GET("list")
    suspend fun getList(
        @Query("page") page: String,
        @Query("limit") limit: String,
        ): Response<JsonArray>


    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor)
                : ApiInterface {
            val interceptor = HttpLoggingInterceptor()
           // interceptor.level = HttpLoggingInterceptor.Level.BODY
            val gson = GsonBuilder().serializeNulls().setLenient().create()
            val okkHttpclient = OkHttpClient.Builder().addInterceptor(networkConnectionInterceptor)
                .connectTimeout(600, TimeUnit.SECONDS)
                .readTimeout(600, TimeUnit.SECONDS)
                .writeTimeout(600, TimeUnit.SECONDS)
                .addInterceptor(interceptor)

                .build()
            return Retrofit.Builder()
                .client(okkHttpclient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(ApiConstant.BASE_URL)
                .build()
                .create(ApiInterface::class.java)

        }
    }


}

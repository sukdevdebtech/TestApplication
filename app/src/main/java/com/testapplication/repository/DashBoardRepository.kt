package com.testapplication.repository

import com.google.gson.JsonArray
import com.google.gson.JsonObject

import com.testapplication.network.ApiInterface
import com.testapplication.network.SafeApiRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class DashBoardRepository(
    private val myApi: ApiInterface
) : SafeApiRequest() {

    suspend fun getList(page:String,limit:String): JsonArray {
        return apiRequest {
            myApi.getList(page,limit)
        }
    }


}
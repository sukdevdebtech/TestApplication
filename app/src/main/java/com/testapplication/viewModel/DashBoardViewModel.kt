package com.testapplication.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.testapplication.repository.DashBoardRepository
import com.testapplication.utils.NoInternetException
import com.testapplication.utils.NoNetworkDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class DashBoardViewModel(private val repository: DashBoardRepository) : ViewModel() {
    lateinit var context: Context
    var errMessage = MutableLiveData<String>()
    var mLoadingStatus = MutableLiveData<Boolean>()

   private var mListRes = MutableLiveData<JsonArray>()
    val getListJsonArrayResponse: LiveData<JsonArray>
        get() = mListRes



    fun getList(limit: String,offset:String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    mLoadingStatus.postValue(true)
                    val inp=Gson().fromJson("",JsonObject::class.java)
                    var response: JsonArray? = null

                    response = repository.getList(limit,offset)



                    response.let {
                        mListRes.postValue(response!!)
                    }

                    mLoadingStatus.postValue(false)
                } catch (e: NoInternetException) {
                    mLoadingStatus.postValue(false)
                    withContext(Dispatchers.Main) {
                        object : NoNetworkDialog(context) {
                            override fun onTryAgain() {
                                getList(limit,offset)
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    mLoadingStatus.postValue(false)
                    Log.e("exception", e.message.toString())
                }
            }
        }
    }

}
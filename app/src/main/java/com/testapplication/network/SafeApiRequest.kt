package com.testapplication.network

import android.util.Log
import com.testapplication.utils.ApiException

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T{
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            val error = response.errorBody()?.string()

            val message = StringBuilder()
            error?.let{
                try{
                    message.append(JSONObject(it).getString("message"))
                }catch(e: JSONException){ }
                message.append("\n")
            }
            message.append("Error Code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }

/*    fun getHeaderTest(): HashMap<String, String> {
        val header: HashMap<String, String> = HashMap();
        header["Authorization"] = "Bearer 03baaeaed348953c9a397581468c638238180a5c76b4223348775a4dc677becf"

        return header
    }*/
}
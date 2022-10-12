package com.testapplication.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.testapplication.R
import com.testapplication.databinding.ActivityMainBinding
import com.testapplication.databinding.ActivitySplashBinding
import com.testapplication.utils.BindActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    val binding: ActivitySplashBinding by BindActivity(R.layout.activity_splash)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.executePendingBindings()
        initViews()
    }


    private fun initViews() {

        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        if(isWorkingInternetPersent()){
            gotoNextActivity()
        }
        else{
            //NoNetworkDialog(this)
        }
    }
    private fun gotoNextActivity() {

        try {

            val SPLASH_DISPLAY_LENGTH  = 1000
            Handler(Looper.getMainLooper()).postDelayed({
                checkUserLoggedin()
            }, SPLASH_DISPLAY_LENGTH.toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun checkUserLoggedin() {

            startActivity(Intent(this, MainActivity::class.java))
            finish()

    }

    fun isWorkingInternetPersent(): Boolean {
        val connectivityManager = baseContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val info = connectivityManager.allNetworkInfo
            if (info != null) for (i in info.indices) if (info[i].state == NetworkInfo.State.CONNECTED) {
                return true
            }
        }
        return false
    }
 /*   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }*/
}
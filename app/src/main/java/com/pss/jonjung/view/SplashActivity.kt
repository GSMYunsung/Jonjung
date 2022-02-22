package com.pss.jonjung.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.pss.jonjung.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(getNetworkConnected(this)){
            CoroutineScope(Dispatchers.IO).launch {
                delay(1500)
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
            }
        }
        else
        {
            Toast.makeText(this, "인터넷 연결 설정을 다시한번 확인해주세요!", Toast.LENGTH_SHORT).show()
        }

    }

    @SuppressLint("ServiceCast")
     fun getNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork : NetworkInfo? = cm.activeNetworkInfo
        val isConnected : Boolean = activeNetwork?.isConnectedOrConnecting == true

        return isConnected

    }
}
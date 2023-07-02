package com.example.ubercloneapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delaySplashScreen()
    }
    fun delaySplashScreen(){
        lifecycleScope.launch {
            delay(5000)
            Toast.makeText(this@SplashScreenActivity, "Splash Screen Done!!", Toast.LENGTH_SHORT).show()
        }
    }
}
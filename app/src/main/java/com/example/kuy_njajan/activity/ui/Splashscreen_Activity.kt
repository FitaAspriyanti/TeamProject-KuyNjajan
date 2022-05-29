@file:Suppress("DEPRECATION")

package com.example.kuy_njajan.activity.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.kuy_njajan.MainActivity
import com.example.kuy_njajan.R

class Splashscreen_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_screen)
        val splashTime: Long = 3000

        Handler().postDelayed({
            val intent = Intent(this, Login_Activity::class.java)
            startActivity(intent)
            finish()
        }, splashTime)

    }
}
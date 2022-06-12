package com.example.kuy_njajan.activity.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.kuy_njajan.R

class HelpActivity : AppCompatActivity() {

    private lateinit var tvEmail : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        tvEmail = findViewById(R.id.tv_email)

        tvEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO,
            Uri.fromParts("mailto", "help.kuynjajan@gmail.com", null))
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
    }
}
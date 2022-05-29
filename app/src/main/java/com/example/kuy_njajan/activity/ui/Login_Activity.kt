package com.example.kuy_njajan.activity.ui

import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.kuy_njajan.R

class Login_Activity : AppCompatActivity(), View.OnClickListener{

    private lateinit var txtDaftar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        txtDaftar = findViewById(R.id.txt_daftar)
        txtDaftar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
       if (v != null){
           when(v.id){
               R.id.txt_daftar-> kotlin.run {
                   val intenRegis  =Intent(this@Login_Activity, Register_Activity::class.java)
                   startActivity(intenRegis)
               }
           }
       }
    }

}

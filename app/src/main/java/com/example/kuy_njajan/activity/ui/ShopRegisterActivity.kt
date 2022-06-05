package com.example.kuy_njajan.activity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kuy_njajan.R
import kotlinx.android.synthetic.main.title.*

class ShopRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_register)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Atur Informasi Toko"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return super.onNavigateUp()
    }
}
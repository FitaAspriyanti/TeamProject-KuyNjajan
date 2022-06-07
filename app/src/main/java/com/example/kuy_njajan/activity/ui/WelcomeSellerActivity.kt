package com.example.kuy_njajan.activity.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.kuy_njajan.R
import kotlinx.android.synthetic.main.title.*

class WelcomeSellerActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_seller)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Selamat Datang di Kuy Lapak!"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val btnDaftarPenjual: Button = findViewById(R.id.btn_daftar_penjual)
        btnDaftarPenjual.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_daftar_penjual -> {
                val intent = Intent(this@WelcomeSellerActivity, StoreRegisterActivity::class.java )
                startActivity(intent)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
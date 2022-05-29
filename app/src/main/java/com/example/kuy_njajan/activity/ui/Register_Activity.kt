package com.example.kuy_njajan.activity.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kuy_njajan.R
import kotlinx.android.synthetic.main.activity_register.*

class Register_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)
        btn_daftar.setOnClickListener {
            register()
        }
    }

    fun register() {
        if (nama_lengkap.text.isEmpty()) {
            nama_lengkap.error = "Kolom Nama tidak boleh kosong"
            nama_lengkap.requestFocus()
            return
        } else if (email.text.isEmpty()) {
            email.error = "Kolom Email tidak boleh kosong"
            email.requestFocus()
            return
        } else if (tlp.text.isEmpty()) {
            tlp.error = "Kolom Nomor Telepon tidak boleh kosong"
            tlp.requestFocus()
            return
        } else if (password.text.isEmpty()) {
            password.error = "Kolom Password tidak boleh kosong"
            password.requestFocus()
            return
        }
    }
}
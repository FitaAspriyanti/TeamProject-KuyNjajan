package com.example.kuy_njajan.activity.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kuy_njajan.MainActivity
import com.example.kuy_njajan.R
import com.example.kuy_njajan.data.ApiConfig
import com.example.kuy_njajan.model.ResponseModel
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.jenkel
import kotlinx.android.synthetic.main.activity_register.nama
import kotlinx.android.synthetic.main.activity_register.notelp
import kotlinx.android.synthetic.main.activity_register.password
import kotlinx.android.synthetic.main.activity_register.username
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)
        txt_login.setOnClickListener {
            startActivity(Intent(this, Login_Activity::class.java))
        }

        btn_daftar.setOnClickListener {
            register()
        }
    }

    fun register() {
        if (nama.text.isEmpty()) {
            nama.error = "Nama Lengkap tidak boleh kosong"
            nama.requestFocus()
            return
        } else if (username.text.isEmpty()) {
            username.error = "Nama Akun tidak boleh kosong"
            username.requestFocus()
            return
        }else if (jenkel.text.isEmpty()) {
            jenkel.error = "Nama Akun tidak boleh kosong"
            jenkel.requestFocus()
            return
        }
        else if (password.text.isEmpty()) {
            password.error = "Password tidak boleh kosong"
            password.requestFocus()
            return
        } else if (notelp.text.isEmpty()) {
            notelp.error = "No Telepon tidak boleh kosong"
            notelp.requestFocus()
            return
        }
        ApiConfig.instanceRetrofit.daftar(nama.text.toString(), jenkel.text.toString(), notelp.text.toString(), username.text.toString(), password.text.toString()).enqueue(object : Callback<ResponseModel>{

            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {

                val respon = response.body()
                if(respon?.success == true){
                    val intent = Intent(this@Register_Activity, Login_Activity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@Register_Activity, "Daftar Akun Berhasil -" + respon.message, Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@Register_Activity, "Daftar Akun Gagal -" + respon?.message, Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@Register_Activity, "Daftar Akun Gagal -" + t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}
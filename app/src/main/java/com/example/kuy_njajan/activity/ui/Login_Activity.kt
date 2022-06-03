package com.example.kuy_njajan.activity.ui

import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kuy_njajan.MainActivity
import com.example.kuy_njajan.R
import com.example.kuy_njajan.data.ApiConfig
import com.example.kuy_njajan.model.ResponseModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_login.username
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login_Activity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        txt_daftar.setOnClickListener {
            startActivity(Intent(this, Register_Activity::class.java))
        }
        btn_masuk.setOnClickListener {
            login()
        }
    }

    fun login(){
        if (username.text.isEmpty()) {
            username.error = "Nama Akun tidak boleh kosong"
            username.requestFocus()
            return
        }else if (password.text.isEmpty()) {
            password.error = "Password tidak boleh kosong"
            password.requestFocus()
            return
        }
        ApiConfig.instanceRetrofit.login().enqueue(object :
            Callback<ResponseModel> {

            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {

                val respon = response.body()!!
                if(respon.success){
                    val intent = Intent(this@Login_Activity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@Login_Activity, "Masuk Akun Berhasil -" + respon.message, Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@Login_Activity, "Nama Akun dan password Salah  -" + respon.message, Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@Login_Activity, "Nama Akun dan password Salah " + t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

}

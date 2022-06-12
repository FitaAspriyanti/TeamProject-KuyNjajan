package com.example.kuy_njajan.activity.ui

import User
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kuy_njajan.MainActivity
import com.example.kuy_njajan.R
import com.example.kuy_njajan.data.ApiConfig
import com.example.kuy_njajan.activity.shared.helper.Constant
import com.example.kuy_njajan.activity.shared.helper.PrefHelper
import com.example.kuy_njajan.model.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_login.username
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login_Activity : AppCompatActivity(){

    private lateinit var ivFacebook: ImageView
    private lateinit var ivGoogle: ImageView
//        lateinit var s: SharedPreferencesLogin

    // perubahan 5
    private val TAG:String = "LoginActivity"
    lateinit var prefHelper:PrefHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ivGoogle = findViewById(R.id.iv_google)

        ivGoogle.setOnClickListener {
            val url = "https://accounts.google.com/signin/v2/identifier?flowName=GlifWebSignIn&flowEntry=AddSession"
            val googleIntent = Intent(Intent.ACTION_VIEW)
            googleIntent.setData(Uri.parse(url))
            startActivity(googleIntent)
        }

        ivFacebook = findViewById(R.id.iv_facebook)

        ivFacebook.setOnClickListener {
            val url = "https://www.facebook.com/"
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            facebookIntent.setData(Uri.parse(url))
            startActivity(facebookIntent)
        }

//            s = SharedPreferencesLogin(this)

        //perubahan 6
        prefHelper = PrefHelper(this)
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
//        ApiConfig.instanceRetrofit.login(username.text.toString(), password.text.toString()).enqueue(object :
//            Callback<ResponUser> {
//            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
//
//                val respon = response.body()!!
//                if(respon.success){
//                    s.setStatus(true)
//                    val intent = Intent(this@Login_Activity, MainActivity::class.java)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//                    startActivity(intent)
//                    finish()
//                    Toast.makeText(this@Login_Activity, "Masuk Akun Berhasil -" + respon.message, Toast.LENGTH_SHORT).show()
//                }else{
//                    Toast.makeText(this@Login_Activity, "Nama Akun dan password Salah  -" + respon.message, Toast.LENGTH_SHORT).show()
//                }
//
//            }
//
//            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
//                Toast.makeText(this@Login_Activity, "Nama Akun dan password Salah " + t.message, Toast.LENGTH_SHORT).show()
//            }
//
//        })

        ApiConfig.instanceRetrofit.login(username.text.toString(), password.text.toString())
            .enqueue(object :Callback<ResponUser> {
                override fun onResponse(call: Call<ResponUser>, response: Response<ResponUser>) {

                    val respon = response.body()!!
                    if(respon.success){
                        printlog(respon.toString())
                        showuser(respon.user)
//                    val datauser = respon.user
//                    for (user in datauser) {
//                      saveSession(respon.user?., user.password, user.idUser, user.nama, user.noTelp)
//                    }
                        moveIntent()
                        Toast.makeText(this@Login_Activity, "Masuk Akun Berhasil -" + respon.message, Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@Login_Activity, "Nama Akun dan password Salah  -" + respon.message, Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<ResponUser>, t: Throwable) {
                    Toast.makeText(this@Login_Activity, "Nama Akun dan password Salah " + t.message, Toast.LENGTH_SHORT).show()
                }
//                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
//                    val respon = response.body()
//                    printlog(respon.toString())
//                }
//
//                override fun onFailure(call: Call<List<User>>, t: Throwable) {
//                    Toast.makeText(this@MainActivity, "Nama Akun dan password Salah " + t.message, Toast.LENGTH_SHORT).show()
//                }

            })
    }

    override fun onStart() {
        super.onStart()
        if (prefHelper.getBoolean( Constant.PREF_IS_LOGIN )) {
            moveIntent()
        }
    }

    private fun saveSession(username: String, password: String, iduser:String, nama: String, notelp:String, foto:String){
        prefHelper.put( Constant.PREF_USERNAME, username )
        prefHelper.put( Constant.PREF_PASSWORD, password )
        prefHelper.put( Constant.pref_iduser, iduser)
        prefHelper.put( Constant.pref_nama, nama)
        prefHelper.put( Constant.pref_notelp, notelp)
        prefHelper.put( Constant.pref_foto, foto)
        prefHelper.put( Constant.PREF_IS_LOGIN, true)
    }

    private fun moveIntent(){
//        startActivity(Intent(this, ::class.java))
//        finish()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun printlog(message: String){
        Log.d(TAG,message)
    }

    private fun showuser(users: List<User>){
        for (user in users){
            printlog("username : ${user.username}\n " +
                    "iduser : ${user.idUser}\n" +
                    "nama :${user.nama}\n" +
                    "notelp: ${user.noTelp}\n"+
            "foto: ${user.foto}")
            saveSession("${user.username}",
                "${user.password}",
                "${user.idUser}",
                "${user.nama}",
                "${user.noTelp}",
            "${user.foto}")

        }
    }


}

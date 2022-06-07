package com.example.kuy_njajan.activity.shared

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import javax.net.ssl.SSLEngineResult

class SharedPreferencesLogin(activity: Activity) {
    val login = "login"
    val shared = "MAIN_SHARED"
    val sp: SharedPreferences

    val nama = "nama"
    val username = "username"
    val notelp = "notelp"

    val user = "user"

    init{
        sp= activity.getSharedPreferences(shared, Context.MODE_PRIVATE)
    }

    fun setStatus(status:Boolean){
        sp.edit().putBoolean(login, status).apply()
    }

    fun getStatus(): Boolean {
        return sp.getBoolean(login, false)
    }


//    fun setUser(value: User) {
//        val data: String = Gson().toJson(value, User::class.java)
//        sp.edit().putString(user, data).apply()
//    }
//
//    fun getUser(): User? {
//        val data:String = sp.getString(user, null) ?: return null
//        return Gson().fromJson<User>(data, User::class.java)
//    }

    fun setString(key: String, value:String) {
        sp.edit().putString(key, value).apply()
    }

    fun getString(key: String): String {
        return sp.getString(key, "")!!
    }

    fun clear(){
        sp.edit().clear().apply()
    }

}
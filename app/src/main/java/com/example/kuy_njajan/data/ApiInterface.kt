package com.example.kuy_njajan.data

import android.system.StructTimespec
import com.example.kuy_njajan.model.ResponUser
import com.example.kuy_njajan.model.ResponseModel
import okhttp3.MultipartBody
import retrofit2.Call
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("user")
    fun daftar(
        @Field("nama") nama: String,
        @Field("jenkel") jenkel: String,
        @Field("notelp") notelp: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseModel>

    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponUser>// perubahan 3

    @GET("dagangan")
    fun getDagangan(
    ): Call<ResponseModel>

    @FormUrlEncoded
    @POST("toko")
    fun daftarToko(
        @Field("nama") nama: String,
        @Field("alamat") alamat: String,
//        @Field("foto") foto: String,
        @Field("notelp") notelp: String,
        @Field("iduser") iduser: String
    ): Call<ResponseModel>

    @Multipart
    //@FormUrlEncoded
    @POST("dagangan")
    fun uploadProduk(
        @Part("nama") nama: String,
        @Part("jenis") jenis: String,
        @Part("asal") asal: String,
        @Part("harga") harga: String,
        @Part("deskripsi") deskripsi: String,
        @Part foto_dagangan: MultipartBody.Part,
        @Part("idtoko") idtoko: String
    ): Call<ResponseModel>
}
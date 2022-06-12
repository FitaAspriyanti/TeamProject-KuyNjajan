package com.example.kuy_njajan.activity.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kuy_njajan.R
import com.example.kuy_njajan.activity.shared.helper.Constant
import com.example.kuy_njajan.activity.shared.helper.PrefHelper
import com.example.kuy_njajan.data.ApiConfig
import com.example.kuy_njajan.model.ResponseModel
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_shop_register.*
import kotlinx.android.synthetic.main.activity_welcome_seller.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterToko : AppCompatActivity() {

    //lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_register)

        btn_simpan_toko.setOnClickListener{
            addNewStore()
        }
    }
    private fun addNewStore() {
        if (et_nama_toko.text.isEmpty()) {
            et_nama_toko.error = "Nama Toko wajib diisi"
            et_nama_toko.requestFocus()
            return
        } else if (et_alamat_toko.text.isEmpty()) {
            et_alamat_toko.error = "Alamat Toko wajib diisi"
            et_alamat_toko.requestFocus()
            return}
//        }else if (et_foto.text.isEmpty()) {
//            et_foto.error = "Alamat Toko wajib diisi"
//            et_foto.requestFocus()
//            return}
        else if (et_notelp.text.isEmpty()) {
            et_notelp.error = "Nomor Telepon wajib diisi"
            et_notelp.requestFocus()
            return
        }  else if (et_id.text.isEmpty()) {
            et_id.error = "Nomor Telepon wajib diisi"
            et_id.requestFocus()
            return
        }

        /*prefHelper = PrefHelper(this)
        val idUser = prefHelper.getString( Constant.pref_iduser )*/

        ApiConfig.instanceRetrofit.daftarToko(
            et_nama_toko.text.toString(),
            et_alamat_toko.text.toString(),et_notelp.text.toString(), et_id.text.toString()//, idUser.toString()
        ).enqueue(
            object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    val response = response.body()
                    if (response?.success == true) {
                        val intent = Intent(this@RegisterToko, UploadProductActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this@RegisterToko, "Pendaftaran Toko Berhassil - " + response.message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    Toast.makeText(this@RegisterToko, "Pendaftaran Toko Gagal, Coba Lagi - " + t.message, Toast.LENGTH_SHORT).show()
                }

            }
        )
    }

}
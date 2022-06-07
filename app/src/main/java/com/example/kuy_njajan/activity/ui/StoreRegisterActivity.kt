package com.example.kuy_njajan.activity.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kuy_njajan.R
import com.example.kuy_njajan.data.ApiConfig
import com.example.kuy_njajan.model.ResponseModel
import kotlinx.android.synthetic.main.activity_shop_register.*
import kotlinx.android.synthetic.main.title.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_register)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Atur Informasi Toko"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        btn_simpan_toko.setOnClickListener {
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
            return
        } else if (et_no_telepon.text.isEmpty()) {
            et_no_telepon.error = "Nomor Telepon wajib diisi"
            et_no_telepon.requestFocus()
            return
        }

        ApiConfig.instanceRetrofit.daftarToko(
            et_nama_toko.text.toString(),
            et_alamat_toko.text.toString(),
            et_no_telepon.text.toString()
        ).enqueue(
            object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    val response = response.body()
                    if (response?.success == true) {
                        val intent = Intent(this@StoreRegisterActivity, AddNewProductActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this@StoreRegisterActivity, "Pendaftaran Toko Berhassil - " + response.message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    Toast.makeText(this@StoreRegisterActivity, "Pendaftaran Toko Gagal, Coba Lagi - " + t.message, Toast.LENGTH_SHORT).show()
                }

            }
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
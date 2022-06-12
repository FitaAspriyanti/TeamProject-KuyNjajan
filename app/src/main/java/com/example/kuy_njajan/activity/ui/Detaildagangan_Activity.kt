package com.example.kuy_njajan.activity.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kuy_njajan.R
import com.example.kuy_njajan.data.room.MyDatabase
import com.example.kuy_njajan.model.Dagangan
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail_dagangan.*
import kotlinx.android.synthetic.main.title.*
import java.text.NumberFormat
import java.util.*

class Detaildagangan_Activity : AppCompatActivity() {
    lateinit var dagangan: Dagangan
    lateinit var myDb: MyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_dagangan)
        myDb= MyDatabase.getInstance(this)!!

        getData()
        mainButton()
    }

    fun mainButton(){
        btn_belisekarang.setOnClickListener{
            val data = myDb.daoBelisekarang().getDagangan(dagangan.idK)
            if(data == null){
             insertData()
            }else{
                data.jumlah = data.jumlah + 1
                updateData(data)
            }
        }
        btn_lanjut.setOnClickListener{
            val intent = Intent( "event: beli")
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            onBackPressed()
        }
    }
    private fun insertData(){
        val myDb: MyDatabase = MyDatabase.getInstance(this)!!

        CompositeDisposable().add(Observable.fromCallable { myDb.daoBelisekarang().insert(dagangan) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                belisekarang()
                Log.d("respons", "data inserted")
                Toast.makeText(this, "Silahkan Klik Button Lanjut", Toast.LENGTH_SHORT).show()
            })
    }

    private fun updateData(data: Dagangan){
         CompositeDisposable().add(Observable.fromCallable { myDb.daoBelisekarang().update(dagangan) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                belisekarang()
                Log.d("respons", "data inserted")
            })
    }

    private fun belisekarang(){
        if (myDb.daoBelisekarang().getAll().isNotEmpty()){
            btn_lanjut.visibility = View.VISIBLE
        }else{
            btn_lanjut.visibility =View.GONE
        }
    }
    private fun getData() {
        val data = intent.getStringExtra("detail")
        dagangan = Gson().fromJson<Dagangan>(data, Dagangan::class.java)

        nama_dagangan.text = dagangan.nama
        harga_dagangan.text =  NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(Integer.valueOf(dagangan.harga))
        asal_dagangan.text = "Khas  " + dagangan.asal
        deskripsi.text = dagangan.deskripsi

        val image = "http://34.143.232.116:8000/images/" + dagangan.foto_dagangan
        Picasso.get()
            .load(image)
            .into(foto_dagangan)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = dagangan.nama
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
package com.example.kuy_njajan.activity.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kuy_njajan.R
import com.example.kuy_njajan.model.Dagangan
import com.example.kuy_njajan.room.MyDatabase
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail_dagangan.*
import kotlinx.android.synthetic.main.title.*

class Detaildagangan_Activity : AppCompatActivity() {
//    lateinit var dbKuyNjajan : DatabaseKuyNjajan
    lateinit var dagangan: Dagangan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_dagangan)
//        dbKuyNjajan = DatabaseKuyNjajan.getInstance(this)!! // call database
//
        getData()
        mainButton()
    }

        fun mainButton(){
        btn_keranjang.setOnClickListener{
            insertData()
        }
            btn_wistlist.setOnClickListener {
                val myDb: MyDatabase = MyDatabase.getInstance(this)!!
                val listData = myDb.daoKeranjang().getAll()
                for(note :Dagangan in listData){
                    println("-----------------------")
                    println(note.nama)
                    println(note.harga)
                }
            }
    }

    fun insertData(){
        val myDb: MyDatabase = MyDatabase.getInstance(this)!!
        val note = Dagangan()
        note.nama= "Makanan Daerah"

        CompositeDisposable().add(Observable.fromCallable { myDb.daoKeranjang().insert(note) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("respons", "data berhasil")
            })
    }

    private fun getData() {
        val data = intent.getStringExtra("detail")
        dagangan = Gson().fromJson<Dagangan>(data, Dagangan::class.java)

        nama_dagangan.text = dagangan.nama
        harga_dagangan.text = dagangan.harga
        asal_dagangan.text = dagangan.asal
        deskripsi.text = dagangan.deskripsi

        val image = "http://192.168.43.146:8000/images/" + dagangan.foto_dagangan
        Picasso.get()
            .load(image)
            .into(foto_dagangan)
//
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
package com.example.kuy_njajan.activity.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kuy_njajan.R
import com.example.kuy_njajan.model.Dagangan
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_dagangan.*
import kotlinx.android.synthetic.main.title.*

class Detaildagangan_Activity : AppCompatActivity() {
//    lateinit var dbKuyNjajan : DatabaseKuyNjajan
////    lateinit var dagangan: Dagangan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_dagangan)
//        dbKuyNjajan = DatabaseKuyNjajan.getInstance(this)!! // call database
//
        getData()
//        mainButton()
    }
    //    fun mainButton(){
//        btn_troli.setOnClickListener{
//        }
//    }
//
//    fun insertData(){
//        val myDb: DatabaseKuyNjajan = DatabaseKuyNjajan.getInstance(this)!!
//        val troli = Dagangan()
//        troli.nama= "Makanan Daerah"
//
//        CompositeDisposable().add(Observable.fromCallable { myDb.daoTroli().insert(troli) }
//            .subscribeOn(Schedulers.computation())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                Log.d("respons", "data berhasil")
//            })
//    }
    private fun getData() {
        val data = intent.getStringExtra("detail")
        val dagangan = Gson().fromJson<Dagangan>(data, Dagangan::class.java)
//        val dagangan = Gson().fromJson<Dagangan>(data, Dagangan::class.java)
        nama_dagangan.text = dagangan.nama
        harga_dagangan.text = dagangan.harga
        asal_dagangan.text = dagangan.asal
//        deskripsi.text = dagangan.deskripsi

        val gbr = "http://192.168.43.146:8080/images" + dagangan.foto_dagangan
        Picasso.get()
            .load(gbr)
            .placeholder(R.drawable.logologin)
            .error(R.drawable.logologin)
            .resize(400, 400)
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
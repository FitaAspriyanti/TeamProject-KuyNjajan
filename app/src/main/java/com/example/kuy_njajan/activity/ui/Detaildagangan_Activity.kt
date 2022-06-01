//package com.example.kuy_njajan.activity.ui
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import com.example.kuy_njajan.Config
//import com.example.kuy_njajan.R
//import com.example.kuy_njajan.database.DatabaseKuyNjajan
//import com.example.kuy_njajan.helper
//import com.google.gson.Gson
//import com.squareup.picasso.Picasso
//import kotlinx.android.synthetic.main.activity_detail_dagangan.*
//import kotlinx.android.synthetic.main.title.*
//
//class Detaildagangan_Activity : AppCompatActivity() {
//    lateinit var dbKuyNjajan : DatabaseKuyNjajan
//    lateinit var dagangan: Dagangan
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail_dagangan)
//        dbKuyNjajan = DatabaseKuyNjajan.getInstance(this)!! // call database
//
//        getInfo()
//    }
//
//    private fun getInfo() {
//        val data = intent.getStringExtra("extra")
//        dagangan = Gson().fromJson<Dagangan>(data, Dagangan::class.java)
//        nama_dagangan.text = dagangan.nama
//        harga_dagangan.text = dagangan.nama
//        deskripsi_dagangan.text = dagangan.deskripsi
//
//        val img = Config.productUrl + dagangan.foto_dagangan
//        Picasso.get()
//            .load(img)
//            .placeholder(R.drawable.ic_baseline_picture_in_picture_24)
//            .error(R.drawable.ic_baseline_picture_in_picture_24)
//            .resize(400, 400)
//            .into(foto_dagangan)
//
//        // setToolbar
//        helper().setToolbar(this, toolbar, dagangan.nama)
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return super.onSupportNavigateUp()
//    }
//}
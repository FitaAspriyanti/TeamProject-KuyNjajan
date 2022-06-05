package com.example.kuy_njajan.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.kuy_njajan.MainActivity
import com.example.kuy_njajan.R
import com.example.kuy_njajan.activity.ui.Detaildagangan_Activity
import com.example.kuy_njajan.model.Dagangan
import com.google.gson.Gson
//import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class AdapterDagangan(var activity: Activity, var data:ArrayList<Dagangan>) : RecyclerView.Adapter<AdapterDagangan.Holder>() {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val namaKuliner = view.findViewById<TextView>(R.id.nama)
        val asalKuliner = view.findViewById<TextView>(R.id.asal)
        val hargaKuliner = view.findViewById<TextView>(R.id.harga)
        val fotoKuliner = view.findViewById<ImageView>(R.id.fotoDagangan)
        val layoutKuliner = view.findViewById<CardView>(R.id.l_dagangan)
//        val deskripsiKuliner = view.findViewById<TextView>(R.id.deskripsi)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_dagangan, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.namaKuliner.text = data[position].nama
        holder.asalKuliner.text = data[position].asal
        holder.hargaKuliner.text = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(Integer.valueOf(data[position].harga))
//        holder.deskripsiKuliner.text = data[position].deskripsi
        val gambar = "http://192.168.0.106:8080/images" + data[position].foto_dagangan
        /*Picasso.get()
            .load(gambar)
            .placeholder(R.drawable.logologin)
            .error(R.drawable.logologin)
            .into(holder.fotoKuliner)*/
        holder.layoutKuliner.setOnClickListener{
            val intent =Intent(activity, Detaildagangan_Activity::class.java )
            val dt = Gson().toJson(data[position], Dagangan::class.java)
            intent.putExtra("detail", dt)

            activity.startActivity(intent)
        }
    }

}



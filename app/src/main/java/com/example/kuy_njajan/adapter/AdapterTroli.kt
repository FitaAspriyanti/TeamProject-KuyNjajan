package com.example.kuy_njajan.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.kuy_njajan.MainActivity
import com.example.kuy_njajan.R
import com.example.kuy_njajan.activity.ui.Detaildagangan_Activity
import com.example.kuy_njajan.model.Dagangan
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class AdapterTroli(var activity: Activity, var data:ArrayList<Dagangan>) : RecyclerView.Adapter<AdapterTroli.Holder>() {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val namaKuliner = view.findViewById<TextView>(R.id.nama)
        val hargaKuliner = view.findViewById<TextView>(R.id.harga)
        val fotoKuliner = view.findViewById<ImageView>(R.id.fotoDagangan)
        val layoutTroli = view.findViewById<LinearLayout>(R.id.l_troli)

        val btnTambah = view.findViewById<ImageView>(R.id.btnTambah)
        val btnKurang = view.findViewById<ImageView>(R.id.btnKurang)
        val checkBox = view.findViewById<CheckBox>(R.id.checkBox)
        val jumlahPembelian = view.findViewById<TextView>(R.id.jumlah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_belisekarang, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.namaKuliner.text = data[position].nama
        holder.hargaKuliner.text = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(Integer.valueOf(data[position].harga))
        val image = "http://192.168.43.146:8000/images/" + data[position].foto_dagangan
        Picasso.get()
            .load(image)
            .into(holder.fotoKuliner)
//        Perbaikan 1
        val jumlah = data[position].jumlah
        holder.jumlahPembelian.text = jumlah.toString()
//    holder.btnTambah.setOnClickListener{
//        jumlah++
//
//        holder.jumlahPembelian.text = jumlah.toString()
//    }
//        holder.btnKurang.setOnClickListener{
//            if
//            jumlah--
//            holder.jumlahPembelian.text = jumlah.toString()
//        }
    }

}



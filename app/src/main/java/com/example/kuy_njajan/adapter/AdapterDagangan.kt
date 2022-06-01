package com.example.kuy_njajan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kuy_njajan.R
import com.example.kuy_njajan.model.DaganganKotlin

class AdapterDagangan( var data:ArrayList<DaganganKotlin>) : RecyclerView.Adapter<AdapterDagangan.Holder>() {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {

        val namaDagangan = view.findViewById<TextView>(R.id.nama_dagangan)
        val khasDagangan = view.findViewById<TextView>(R.id.khas_dagangan)
        val hargaDagangan = view.findViewById<TextView>(R.id.harga_dagangan)
        val fotoDagangan = view.findViewById<ImageView>(R.id.foto_dagangan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_dagangan, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.namaDagangan.text = data[position].nama_dagangan
        holder.khasDagangan.text = data[position].khas_dagangan
        holder.hargaDagangan.text = data[position].harga_dagangan
        holder.fotoDagangan.setImageResource(data[position].foto_dagangan)
//        val image = Config.productUrl + data[position].fotoDagangan
//        Picasso.get()
//            .load(image)
//            .placeholder(R.drawable.ic_baseline_picture_in_picture_24)
//            .error(R.drawable.ic_baseline_picture_in_picture_24)
//            .into(holder.fotoDagangan)

//        holder.lDagangan.setOnClickListener {
//            val activiti = Intent(activity, Detaildagangan_Activity::class.java)
//            val str = Gson().toJson(data[position], Dagangan::class.java)
//            activiti.putExtra("extra", str)
//            activity.startActivity(activiti)
//        }
    }

}
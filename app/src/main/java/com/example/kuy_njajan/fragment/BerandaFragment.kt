package com.example.kuy_njajan.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kuy_njajan.R
import com.example.kuy_njajan.adapter.AdapterDagangan
import com.example.kuy_njajan.data.ApiConfig
import com.example.kuy_njajan.model.DaganganKotlin
import com.example.kuy_njajan.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [BerandaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BerandaFragment : Fragment() {
    //    lateinit var rvKlaris: RecyclerView
    lateinit var rvKbaru: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_beranda, container, false)
//        init(view)
//        getDagangan()
//        tampilData()
        rvKbaru = view.findViewById(R.id.rv_kulinerbaru)

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        rvKbaru.adapter = AdapterDagangan(arrDagangan)
        rvKbaru.layoutManager = layoutManager
        return view

    }

    val arrDagangan: ArrayList<DaganganKotlin>get(){
        val arr= ArrayList<DaganganKotlin>()
        val d1= DaganganKotlin()
        d1.nama_dagangan = "Combro"
        d1.khas_dagangan ="Bandung-Jawa Barat"
        d1.harga_dagangan ="Rp. 10.000"
        d1.foto_dagangan = R.drawable.combro

        val d2= DaganganKotlin()
        d2.nama_dagangan = "Putri Ayu"
        d2.khas_dagangan ="Bandung-Jawa Barat"
        d2.harga_dagangan ="Rp. 10.000"
        d2.foto_dagangan = R.drawable.putriayu

        arr.add(d1)
        arr.add(d2)


        return arr
    }
//    fun tampilData(){
//
//        val layoutManager = LinearLayoutManager(activity)
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
//
//        val layoutManager2 = LinearLayoutManager(activity)
//        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL
//
////        rvKlaris.adapter = AdapterProduk(requireActivity(), listProduk)
////        rvKlaris.layoutManager = layoutManager
////
////        rvKbaru.adapter = AdapterProduk(requireActivity(), listProduk)
////        rvKbaru.layoutManager = layoutManager2
//
//    }
//
//    fun getDagangan() {
//        ApiConfig.instanceRetrofit.getdagangan().enqueue(object : Callback<ResponseModel> {
//            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
//            }
//
//            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
//                val res = response.body()!!
//
//            }
//        })
//    }
//
//    fun init(view: View) {
//        rvKlaris = view.findViewById(R.id.rv_kulinerlaris)
//        rvKbaru = view.findViewById(R.id.rv_kulinerbaru)
//    }
}
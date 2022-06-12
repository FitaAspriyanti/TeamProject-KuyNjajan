package com.example.kuy_njajan.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kuy_njajan.R
import com.example.kuy_njajan.activity.shared.helper.Constant
import com.example.kuy_njajan.activity.shared.helper.PrefHelper
import com.example.kuy_njajan.adapter.AdapterDagangan
import com.example.kuy_njajan.data.ApiConfig
import com.example.kuy_njajan.model.Dagangan
import com.example.kuy_njajan.model.ResponseModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_dagangan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BerandaFragment : Fragment() {
    lateinit var prefHelper: PrefHelper
    lateinit var rvKbaru: RecyclerView
    lateinit var  textNama : TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view: View = inflater.inflate(R.layout.fragment_beranda, container, false)
        init(view)
        prefHelper = PrefHelper(requireActivity())
        rvKbaru = view.findViewById(R.id.rv_kulinerbaru)
        getdagangan()

        textNama.text = prefHelper.getString( Constant.pref_nama )
        return view

    }
    fun displayData(){

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvKbaru.adapter = AdapterDagangan(requireActivity(), listDagangan)
        rvKbaru.layoutManager = layoutManager

    }

    private var listDagangan:ArrayList<Dagangan> = ArrayList()
    private fun getdagangan(){
        ApiConfig.instanceRetrofit.getDagangan().enqueue(object :
            Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val res = response.body()!!
                if(res.success==true){
                    listDagangan = res.datadagangan
                    displayData()
                }
            }
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {

            }
        })
    }
fun init(view: View){
    textNama= view.findViewById(R.id.namauser)
}
}
package com.example.kuy_njajan.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kuy_njajan.R
import com.example.kuy_njajan.adapter.AdapterDagangan
import com.example.kuy_njajan.adapter.AdapterTroli
import com.example.kuy_njajan.data.room.MyDatabase

class TroliFragment : Fragment() {
    lateinit var btnHapus : ImageView
    lateinit var rvDagangan : RecyclerView
    lateinit var total : TextView
    lateinit var btnPesan : Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_keranjang, container, false)
        init(view)

        setButton()
        getDagangan()
        return view
    }
    fun getDagangan(){
        val myDb = MyDatabase.getInstance(requireActivity())
        val listDagangan = myDb!!.daoBelisekarang().getAll() as ArrayList
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvDagangan.adapter = AdapterTroli(requireActivity(), listDagangan)
        rvDagangan.layoutManager = layoutManager
    }
    fun setButton(){
        btnHapus.setOnClickListener{

        }
        btnPesan.setOnClickListener{

        }
    }
    fun init(view: View){
        btnHapus =view.findViewById(R.id.btnHapus)
        rvDagangan =view.findViewById(R.id.rvDagangan)
        total =view.findViewById(R.id.total)
        btnPesan =view.findViewById(R.id.btnPesan)

    }

    override fun onResume() {
        getDagangan()
        super.onResume()
    }

}
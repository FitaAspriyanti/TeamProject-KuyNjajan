package com.example.kuy_njajan.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kuy_njajan.R
import com.example.kuy_njajan.adapter.AdapterTroli
import com.example.kuy_njajan.data.room.MyDatabase

class WistlistFragment : Fragment() {
    lateinit var btnHapus : ImageView
    lateinit var rvWistlist : RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_wistlist, container, false)
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

        rvWistlist.adapter = AdapterTroli(requireActivity(), listDagangan)
        rvWistlist.layoutManager = layoutManager
    }
    fun setButton(){
        btnHapus.setOnClickListener{

        }

    }
    fun init(view: View){
        btnHapus =view.findViewById(R.id.btnHapus)
        rvWistlist =view.findViewById(R.id.rvWistlist)

    }

    override fun onResume() {
        getDagangan()
        super.onResume()
    }
}
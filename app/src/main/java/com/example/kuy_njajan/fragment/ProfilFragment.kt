package com.example.kuy_njajan.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import com.example.kuy_njajan.R
import com.example.kuy_njajan.activity.shared.*
import com.example.kuy_njajan.activity.ui.Login_Activity
import com.example.kuy_njajan.activity.ui.RegisterToko
import com.example.kuy_njajan.activity.ui.WelcomeSellerActivity

class ProfilFragment : Fragment() {

    lateinit var s: SharedPreferencesLogin
    lateinit var btnLogout: Button
    lateinit var btnMulaiJual: RelativeLayout
    lateinit var btnRiwayat : RelativeLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_profil, container, false)
        init(view)

        s = SharedPreferencesLogin(requireActivity())
        btnLogout.setOnClickListener {
            s.setStatus(false)
            startActivity(Intent(requireActivity(), Login_Activity::class.java))
        }

        btnMulaiJual.setOnClickListener {
            startActivity(Intent(requireActivity(), WelcomeSellerActivity::class.java))
        }
        btnRiwayat.setOnClickListener{
//            startActivity(Intent(requireActivity(), RegisterToko::class.java))
        }
        return view
    }

    private fun init(view: View) {
        btnLogout = view.findViewById(R.id.btn_logout)
        btnMulaiJual = view.findViewById(R.id.btn_mulaijual)
        btnRiwayat = view.findViewById(R.id.btn_riwayat)
    }

}
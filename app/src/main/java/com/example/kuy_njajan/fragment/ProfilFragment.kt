package com.example.kuy_njajan.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.kuy_njajan.R
import com.example.kuy_njajan.activity.ui.WelcomeSellerActivity
import com.example.kuy_njajan.databinding.FragmentProfilBinding
import kotlinx.android.synthetic.main.fragment_profil.*

class ProfilFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = FragmentProfilBinding.inflate(layoutInflater)

        bind.tvMulaiJual.setOnClickListener {
            val intent = Intent(this@ProfilFragment.requireContext(), WelcomeSellerActivity::class.java)
            startActivity(intent)
        }

        return bind.root
    }
}
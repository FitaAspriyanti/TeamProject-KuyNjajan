package com.example.kuy_njajan

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.kuy_njajan.fragment.BerandaFragment
import com.example.kuy_njajan.fragment.ProfilFragment
import com.example.kuy_njajan.fragment.WistlistFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val fragmentBeranda: Fragment = BerandaFragment()
    private val fragmentWistlist: Fragment = WistlistFragment()
    private var fragmentProfil: Fragment = ProfilFragment()
    private val fm: FragmentManager = supportFragmentManager
    private var active: Fragment = fragmentBeranda

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNav()
    }
    fun setUpBottomNav() {
        fm.beginTransaction().add(R.id.container_fragment, fragmentBeranda)
            .show(fragmentBeranda).commit()
        fm.beginTransaction().add(R.id.container_fragment, fragmentWistlist)
            .hide(fragmentWistlist).commit()
        fm.beginTransaction().add(R.id.container_fragment, fragmentProfil).hide(fragmentProfil)
            .commit()

        bottomNavigationView = findViewById(R.id.Navigation)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.navigation_beranda -> {
                    callFargment(0, fragmentBeranda)
                }
                R.id.navigation_wistlist -> {
                    callFargment(1, fragmentWistlist)
                }
                R.id.navigation_profil -> {
                    callFargment(2, fragmentProfil)
                }
            }

            false
        }
    }
    fun callFargment(int: Int, fragment: Fragment) {
        menuItem = menu.getItem(int)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}
package com.deviceadminapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.hide()

        val bNav = findViewById<BottomNavigationView>(R.id.bottom_nav_dashboard)
        bNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_login_attempt ->{
                    Intent(this, LoginAttemptsActivity :: class.java).apply {
                        startActivity(this)
                    }
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener true
                }
            }
        }
        bNav.selectedItemId = R.id.menu_dashboard

    }
}
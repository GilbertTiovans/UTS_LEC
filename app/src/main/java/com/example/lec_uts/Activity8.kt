package com.example.lec_uts

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity8 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_8)
    }

    fun goToActivity9(view: View) {
        val intent = Intent(this, Activity9::class.java)
        startActivity(intent)

    }

    fun goToActivity7(view: View) {
        val intent = Intent(this, Activity7::class.java)
        startActivity(intent)
    }
}
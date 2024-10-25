package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lec_uts.Activity11
import com.example.lec_uts.R

class Activity10 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_10)

    }
    fun goToActivity11(view: View) {
        val intent = Intent(this, Activity11::class.java)
        startActivity(intent)
    }
}
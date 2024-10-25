package com.example.lec_uts
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class Activity9 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_9)

    }
    fun goToActivity10(view: View) {
        val intent = Intent(this, Activity10::class.java)
        startActivity(intent)
    }
    fun goToActivity12(view: View) {
        val intent = Intent(this, Activity12::class.java)
        startActivity(intent)
    }
}
package com.example.lec_uts


import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AboutusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aboutus) // Make sure this matches your XML file name

        // Inisialisasi backButton
        val backButton: ImageView = findViewById(R.id.backButton)

        // Set click listener untuk backButton
        backButton.setOnClickListener {
            finish() // Kembali ke aktivitas sebelumnya
        }
    }
}

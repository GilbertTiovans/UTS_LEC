package com.example.lec_uts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.lec_uts.R

class OnBoard3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_onboard3) // Menggunakan layout fragment_onboard1 untuk tampilan activity

        // Inisialisasi tombol "Next"
        val nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            // Navigasi ke Onboard2Activity (jika ada)
            val intent = Intent(this, CreateOrLoginActivity::class.java) // Sesuaikan nama activity berikutnya
            startActivity(intent)
        }
    }
}
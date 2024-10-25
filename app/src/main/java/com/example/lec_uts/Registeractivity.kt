package com.example.lec_uts


import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registeractivity)

        // Inisialisasi view
        val backIcon: ImageView = findViewById(R.id.backIcon)
        val editProfileButton: Button = findViewById(R.id.editProfileButton)
        val aboutUsLayout: ViewGroup = findViewById(R.id.aboutUsLayout)

        // Set onClickListener untuk tombol kembali
        backIcon.setOnClickListener {
            finish() // Menutup aktivitas saat ini dan kembali ke aktivitas sebelumnya
        }

        // Set onClickListener untuk tombol edit profil
        editProfileButton.setOnClickListener {
            val intent = Intent(this, MyProfileActivity::class.java)
            startActivity(intent)
        }

        // Set onClickListener untuk About Us
        aboutUsLayout.setOnClickListener {
            val intent = Intent(this, AboutusActivity::class.java)
            startActivity(intent)
        }
    }
}

package com.example.lec_uts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.lec_uts.R

class CreateOrLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_create_or_login)


        val nextButton: Button = findViewById(R.id.create_button)
        nextButton.setOnClickListener {

            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }
    }
}
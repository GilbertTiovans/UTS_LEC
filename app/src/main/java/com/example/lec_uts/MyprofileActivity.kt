// MyProfileActivity.kt
package com.example.lec_uts



import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MyProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myprofile)

        // Ensure the correct view IDs are used
        val backButton: ImageButton = findViewById(R.id.backButton)
        val editButton: ImageButton = findViewById(R.id.editButton)

        // Set onClickListener for back button
        backButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        editButton.setOnClickListener {
            val intent = Intent(this, UpdateProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
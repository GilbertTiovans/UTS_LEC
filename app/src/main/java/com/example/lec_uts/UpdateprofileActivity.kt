package com.example.lec_uts


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UpdateProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updateprofile)

        // Initialize views
        val lastNameInput: EditText = findViewById(R.id.lastNameInput)
        val emailInput: EditText = findViewById(R.id.emailInput)
        val editProfileButton: Button = findViewById(R.id.editProfileButton)
        val backtoprofile: ImageButton = findViewById(R.id.backButton)
        backtoprofile.setOnClickListener {
            val intent = Intent(this, UpdateProfileActivity::class.java)
            startActivity(intent)
        }
        // Set onClickListener for the edit profile button
        editProfileButton.setOnClickListener {
            val lastName = lastNameInput.text.toString()
            val email = emailInput.text.toString()

            // Perform profile update logic here
            // For example, save the data to a database or send it to a server

            // Show a confirmation message
            Toast.makeText(this, "Profile updated: $lastName, $email", Toast.LENGTH_SHORT).show()
        }
    }
}
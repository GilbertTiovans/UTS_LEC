package com.example.lec_uts

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_7)


    }
    fun goToActivity8(view: View) {
        val intent = Intent(this, Activity8::class.java)
        startActivity(intent)
    }
    fun goToWorkoutActivity(view: View) {
        val intent = Intent(this, WorkoutActivity::class.java)
        startActivity(intent)
    }
}
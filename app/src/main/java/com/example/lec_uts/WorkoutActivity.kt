package com.example.lec_uts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class WorkoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        // Access the CardView and set its click listener
        val otherExerciseCard: CardView = findViewById(R.id.img_other_exercise)
        otherExerciseCard.setOnClickListener {
            val intent = Intent(this, OtherExerciseActivity::class.java)
            startActivity(intent)
        }

        // Access another CardView for Cardio and set its click listener
        val cardioCard: CardView = findViewById(R.id.img_cardio)
        cardioCard.setOnClickListener {
            val intent = Intent(this, Activity7::class.java)
            startActivity(intent)
        }
    }
}

package com.example.lec_uts


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class WorkoutActivity : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout) // Make sure this matches your layout XML filename

        // Find the "Other Exercise" CardView
        val otherExerciseCard: CardView = findViewById(R.id.img_other_exercise)

        // Set OnClickListener to navigate to OtherExerciseActivity when clicked
        otherExerciseCard.setOnClickListener {
            val intent = Intent(this, OtherExerciseActivity::class.java)
            startActivity(intent)  // Start the new Activity
        }

        // Additional setup if needed (e.g., toolbar setup, other click listeners)
    }
}

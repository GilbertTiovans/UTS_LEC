package com.example.lec_uts

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AddNewExerciseActivity : AppCompatActivity() {

    // Get Firestore instance
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_exercise)

        // Get references to input fields and button
        val nameInput: EditText = findViewById(R.id.input_exercise_name)
        val descriptionInput: EditText = findViewById(R.id.input_exercise_description)
        val caloriesInput: EditText = findViewById(R.id.input_calories)
        val addButton: Button = findViewById(R.id.btn_add_exercise)

        // Set OnClickListener to add new exercise
        addButton.setOnClickListener {
            val exerciseName = nameInput.text.toString().trim()
            val exerciseDescription = descriptionInput.text.toString().trim()
            val exerciseCalories = caloriesInput.text.toString().trim()

            // Convert calories input to Double
            val caloriesValue = exerciseCalories.toDoubleOrNull()

            // Simple validation
            if (exerciseName.isEmpty() || exerciseDescription.isEmpty() || exerciseCalories.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else if (caloriesValue == null) {
                Toast.makeText(this, "Please enter a valid number for calories", Toast.LENGTH_SHORT).show()
            } else {
                // Create a map to store exercise data
                val exercise = hashMapOf(
                    "name" to exerciseName,
                    "description" to exerciseDescription,
                    "calories" to caloriesValue
                )

                // Write to Firestore (in a "Exercises" collection)
                db.collection("Exercises")
                    .add(exercise)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Exercise added successfully!", Toast.LENGTH_SHORT).show()
                        setResult(RESULT_OK) // Set result to indicate success
                        finish() // Close the activity
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Failed to add exercise: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}

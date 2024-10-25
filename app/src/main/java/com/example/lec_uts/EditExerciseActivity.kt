package com.example.lec_uts

import Exercise
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class EditExerciseActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var editTextName: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var editTextCalorie: EditText
    private lateinit var buttonSave: Button

    private lateinit var exercise: Exercise

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_exercise)

        // Inisialisasi Firestore
        db = FirebaseFirestore.getInstance()

        // Inisialisasi view
        editTextName = findViewById(R.id.editText_name)
        editTextDescription = findViewById(R.id.editText_description)
        editTextCalorie = findViewById(R.id.editText_calorie)
        buttonSave = findViewById(R.id.button_save)

        // Dapatkan data exercise dari Intent
        exercise = intent.getSerializableExtra("exercise") as? Exercise
            ?: run {
                Toast.makeText(this, "Error loading exercise", Toast.LENGTH_SHORT).show()
                finish()
                return
            }

        // Isi form dengan data exercise yang ada
        editTextName.setText(exercise.name)
        editTextDescription.setText(exercise.description)
        editTextCalorie.setText(exercise.calories.toString())

        // Tombol untuk menyimpan perubahan
        buttonSave.setOnClickListener {
            saveChanges()
        }
    }

    private fun saveChanges() {
        val newName = editTextName.text.toString().trim()
        val newDescription = editTextDescription.text.toString().trim()
        val newCalorie = editTextCalorie.text.toString().toDoubleOrNull()

        if (exercise.id.isNotEmpty() && newCalorie != null) {
            val updatedExercise = exercise.copy(
                name = newName,
                description = newDescription,
                calories = newCalorie
            )

            // Update dokumen di Firestore dengan ID yang benar
            db.collection("Exercises").document(exercise.id)
                .set(updatedExercise)
                .addOnSuccessListener {
                    Toast.makeText(this, "Exercise updated successfully", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK) // Set result to indicate success
                    finish() // Close the activity
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failed to update exercise: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "Invalid document ID or calorie input", Toast.LENGTH_SHORT).show()
        }
    }
}

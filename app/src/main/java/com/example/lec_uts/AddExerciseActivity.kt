package com.example.lec_uts
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class AddExerciseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)  // Make sure this matches your layout XML filename


    }

    fun goBack(view: View) {
        val intent = Intent(this, OtherExerciseActivity::class.java)
        startActivity(intent)
    }

    fun GoToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
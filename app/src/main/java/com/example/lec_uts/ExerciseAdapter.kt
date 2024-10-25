package com.example.lec_uts

import Exercise
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter(
    private val exerciseList: List<Exercise>,
    private val onAddClick: (Exercise) -> Unit
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.exercise_name)
        val description: TextView = itemView.findViewById(R.id.exercise_description)
        val calories: TextView = itemView.findViewById(R.id.exercise_calories)
        val addButton: ImageButton = itemView.findViewById(R.id.btn_add_exercise)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exerciseList[position]
        holder.name.text = exercise.name
        holder.description.text = "Description : ${exercise.description}"
        holder.calories.text = "Calories Burned : ${exercise.calories.toString()}" // Assuming calories is an Int

        // Handle click for the add button
        holder.addButton.setOnClickListener {
            // Use holder.itemView.context to get the correct context
            val intent = Intent(holder.itemView.context, AddExerciseActivity::class.java)
            holder.itemView.context.startActivity(intent)
            // Alternatively, call the onAddClick callback if you want to handle it outside
            // onAddClick(exercise)
        }
    }

    override fun getItemCount() = exerciseList.size
}
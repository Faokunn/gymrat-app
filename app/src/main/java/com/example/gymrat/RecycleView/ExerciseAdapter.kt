package com.example.gymrat.RecycleView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymrat.R
import com.example.gymrat.model.ExercisesData

// ExerciseAdapter.kt
class ExerciseAdapter(private var exercises: List<ExercisesData>) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Declare and initialize your UI elements here
        val exerciseNameTextView: TextView = itemView.findViewById(R.id.exerciseNameTextView)
        val groupMuscleTextView: TextView = itemView.findViewById(R.id.groupMuscleTextView)
        val targetMuscleTextView: TextView = itemView.findViewById(R.id.targetMuscleTextView)
       // val properFormTextView: TextView = itemView.findViewById(R.id.exerciseNameTextView)
        // Add other UI elements as needed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]

        holder.exerciseNameTextView.text = exercise.exerciseName
        holder.groupMuscleTextView.text = exercise.groupMuscle
        holder.targetMuscleTextView.text = exercise.targetMuscle
        //holder.properFormTextView.text = exercise.properForm
    }

    override fun getItemCount(): Int {
        return exercises.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newExercises: List<ExercisesData>) {
        exercises = newExercises
        notifyDataSetChanged()
    }
}

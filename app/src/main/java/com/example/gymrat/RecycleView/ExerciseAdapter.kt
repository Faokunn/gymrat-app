package com.example.gymrat.RecycleView

import android.annotation.SuppressLint
import com.example.gymrat.api.RetrofitClient
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.gymrat.Models.AuthManager
import com.example.gymrat.Models.LoginResponse
import com.example.gymrat.R
import com.example.gymrat.model.ExercisesData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// ExerciseAdapter.kt
class ExerciseAdapter(private var exercises: List<ExercisesData>) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseNameTextView: TextView = itemView.findViewById(R.id.exerciseNameTextView)
        val groupMuscleTextView: TextView = itemView.findViewById(R.id.groupMuscleTextView)
        val targetMuscleTextView: TextView = itemView.findViewById(R.id.targetMuscleTextView)
        val properFormTextView: TextView = itemView.findViewById(R.id.exerciseNameTextView)
        val formButton: Button = itemView.findViewById(R.id.formButton)
        val addButton: Button = itemView.findViewById(R.id.addButton)

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

        holder.formButton.setOnClickListener {
            showFormDialog(holder.itemView.context, exercise.properForm)
        }
        holder.addButton.setOnClickListener {
            showAddDialog(holder.itemView.context, exercise)
        }
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    private fun showFormDialog(context: Context, properForm: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Exercise Form")
        builder.setMessage(properForm)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    @SuppressLint("MissingInflatedId")
    private fun showAddDialog(context: Context, exercise: ExercisesData) {
        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.dialog_add_exercise, null)

        val setsEditText: EditText = dialogView.findViewById(R.id.setsEditText)
        val repsEditText: EditText = dialogView.findViewById(R.id.repsEditText)
        val weightEditText: EditText = dialogView.findViewById(R.id.weightEditText)

        builder.setView(dialogView)
        builder.setTitle("Add ${exercise.exerciseName}")
        builder.setPositiveButton("Add") { dialog, _ ->
            val sets = setsEditText.text.toString().toIntOrNull() ?: 0
            val reps = repsEditText.text.toString().toIntOrNull() ?: 0
            val weight = weightEditText.text.toString().toIntOrNull() ?: 0
            val programId = AuthManager.instance.programid
            if (programId != null) {
                addExerciseToProgram(context, exercise, sets, reps, weight, programId)
            }
            else{
                Toast.makeText(context, "here", Toast.LENGTH_SHORT).show()
            }

            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun addExerciseToProgram(
        context: Context,
        exercise: ExercisesData,
        sets: Int,
        reps: Int,
        weight: Int,
        programid: Int
    ) {
        val api = RetrofitClient.instance

        api.exerciseAdd(exercise.groupMuscle.lowercase(),exercise.exerciseName, exercise.targetMuscle, sets, reps, weight, programid)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "${exercise.exerciseName} Added To Your Program", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Failed to add exercise. Please try again.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(context, "Network error. Please try again later.", Toast.LENGTH_SHORT).show()
                }
            })
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newExercises: List<ExercisesData>) {
        exercises = newExercises.sortedBy { it.targetMuscle }
        notifyDataSetChanged()
    }
}

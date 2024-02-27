package com.example.gymrat.model

import com.google.gson.annotations.SerializedName

data class ProgramExerciseData(
    val id: Int,
    val exercise: String,
    val targetMuscle: String,
    val sets: Int,
    val reps: Int,
    val weight: Int,
)


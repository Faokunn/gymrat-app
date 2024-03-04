package com.example.gymrat.model

import com.google.gson.annotations.SerializedName

data class ProgramExerciseData(
    val id: Int,
    val exercise: String,
    @SerializedName("group_muscle") val GroupMuscle: String,
    @SerializedName("target_muscle") val targetMuscle: String,
    val sets: Int,
    val reps: Int,
    val weight: Int,
    val program_goal: String,
    val proper_form: String
)


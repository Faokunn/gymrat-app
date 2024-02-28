package com.example.gymrat.Models

import com.example.gymrat.model.ProgramExerciseData
import com.google.gson.annotations.SerializedName

data class ProgramExercisesResponse(
    @SerializedName("programexercises") val exercises: List<ProgramExerciseData>
)
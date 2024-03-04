package com.example.gymrat.Models

import com.example.gymrat.model.ExercisesData
import com.example.gymrat.model.TopExercisesData
import com.google.gson.annotations.SerializedName

data class TopExercisesResponse(
    @SerializedName("topExercisesByGroup") val exercises: Map<String, List<TopExercisesData>>
)
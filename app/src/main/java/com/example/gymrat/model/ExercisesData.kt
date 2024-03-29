package com.example.gymrat.model

import com.google.gson.annotations.SerializedName

data class ExercisesData(
    val id: Int,
    @SerializedName("ExerciseName") val exerciseName: String,
    @SerializedName("GroupMuscle") val groupMuscle: String,
    @SerializedName("TargetMuscle") val targetMuscle: String,
    @SerializedName("ProperForm") val properForm: String,
    @SerializedName("Environment") val environment: String,
    @SerializedName("image") val image: String,
    @SerializedName("formImage") val formImage: String
)


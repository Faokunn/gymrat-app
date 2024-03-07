package com.example.gymrat.model

import com.google.gson.annotations.SerializedName

data class TopExercisesData(
    @SerializedName("Exercise") val exerciseName: String,
    @SerializedName("GroupMuscle") val groupMuscle: String,
    @SerializedName("TargetMuscle") val targetMuscle: String,
    val environment: String,
    @SerializedName("Count") val count: Int,
    val ProperForm:String,
    val image:String,
    val formImage:String,
)


package com.example.gymrat.Models

import com.example.gymrat.model.ProgramExerciseData

data class User(
    val id: Int,
    val email: String,
    val profile: ProfileResponse,
    val calories: Calories?,
    val program: Program?
)
data class Profile(
    val id: Int,
    val nickname: String,
    val age: String,
    val gender: String,
    val goal: String,
    val weight: Int,
    val goal_weight: Int,
    val height:Int,
    val environment: String

)

data class Program(
    val id:Int,
    val goal: String,
    val title:String
)

data class Calories(
    val id:Int,
    val maintenance: String,
    val surplus: String,
    val deficit: String,
)

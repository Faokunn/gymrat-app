package com.example.gymrat.Models

import com.example.gymrat.model.ProgramExerciseData

data class User(
    val id: Int,
    val email: String,
    val profile: ProfileResponse,
    val program: Program?
)
data class Profile(
    val id:Int,
    val nickname: String,
    val age: String,
    val gender: String,
    val goal: String,

)

data class Program(
    val id:Int,
    val goal: String,
    val title:String
)

data class Chest(
    val id:Int,
    val exercise:String,
    val target_muscle:String,
    val sets:Int,
    val reps:Int,
    val weight:Int,
)

data class Shoulder(
    val id:Int,
    val exercise:String,
    val target_muscle:String,
    val sets:Int,
    val reps:Int,
    val weight:Int,
)

data class Tricep(
    val id:Int,
    val exercise:String,
    val target_muscle:String,
    val sets:Int,
    val reps:Int,
    val weight:Int,
)

data class Back(
    val id:Int,
    val exercise:String,
    val target_muscle:String,
    val sets:Int,
    val reps:Int,
    val weight:Int,
)

data class Bicep(
    val id:Int,
    val exercise:String,
    val target_muscle:String,
    val sets:Int,
    val reps:Int,
    val weight:Int,
)

data class Leg(
    val id:Int,
    val exercise:String,
    val target_muscle:String,
    val sets:Int,
    val reps:Int,
    val weight:Int,
)


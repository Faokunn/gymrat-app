package com.example.gymrat.Models

data class User(
    val id: Int,
    val email: String,
    val profile: Profile,
    val program: Program
)
data class Profile(
    val id:Int,
    val nickname: String,
    val age: String,
    val gender: String
)

data class Program(
    val id:Int,
    val title:String,
    val chest: Chest,
    val shoulder: Shoulder,
    val tricep: Tricep,
    val back: Back,
    val bicep: Bicep,
    val leg: Leg,
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
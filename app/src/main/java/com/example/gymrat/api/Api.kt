package com.example.gymrat.api

import com.example.gymrat.Models.DefaultResponse
import com.example.gymrat.Models.ExercisesResponse
import com.example.gymrat.Models.LoginResponse
import com.example.gymrat.Models.ProfileResponse
import com.example.gymrat.Models.ProgramExercisesResponse
import com.example.gymrat.Models.ProgramResponse
import com.example.gymrat.model.ExercisesData
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface Api {

    @FormUrlEncoded
    @POST("api/register")
    fun createUser(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirm_password") cpassword: String,
        @Field("profile[nickname]") nickname: String,
        @Field("profile[age]") age: String,
        @Field("profile[gender]") gender: String,
        @Field("program[title]") title: String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("api/login")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String,
    ):Call<LoginResponse>

    @FormUrlEncoded
    @POST("api/{groupMuscle}")
    fun exerciseAdd(
        @Path("groupMuscle") groupMuscle: String,
        @Field("exercise") exercise: String,
        @Field("target_muscle") targetMuscle: String,
        @Field("sets") sets: Int,
        @Field("reps") reps: Int,
        @Field("weight") weight: Int,
        @Field("program_id") programId: Int
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("api/logout")
    fun userLogout(

    ):Call<LoginResponse>

    @GET("api/profile/{user_id}")
    fun getUserProfile(@Path("user_id") userId: Int): Call<ProfileResponse>

    @GET("api/program/{user_id}")
    fun getUserProgram(@Path("user_id") userId: Int): Call<ProgramResponse>

    @GET("api/exercise/{groupMuscle}")
    fun getExercises(@Path("groupMuscle") groupMuscle: String): Call<ExercisesResponse>

}
package com.example.gymrat.api

import com.example.gymrat.Models.CaloriesResponse
import com.example.gymrat.Models.DefaultResponse
import com.example.gymrat.Models.ExercisesResponse
import com.example.gymrat.Models.LoginResponse
import com.example.gymrat.Models.ProfileResponse
import com.example.gymrat.Models.ProgramExercisesResponse
import com.example.gymrat.Models.ProgramResponse
import com.example.gymrat.Models.TopExercisesResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
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
        @Field("profile[goal]") goal: String,
        @Field("profile[weight]") weight: Int,
        @Field("profile[goal_weight]") goal_weight: Int,
        @Field("profile[height]") height: Int,
        @Field("profile[environment]") environemnt: String,
        @Field("profile[gender]") gender: String,
        @Field("program[title]") title: String,
        @Field("program[goal]") pgoal: String,
        @Field("calories[maintenance]") maintenance: Int,
        @Field("calories[surplus]") surplus: Int,
        @Field("calories[deficit]") deficit: Int

    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("api/login")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String,
    ):Call<LoginResponse>

    @FormUrlEncoded
    @PUT("api/ProgramExercises/{id}")
    fun updateProgramExercise(
        @Path("id") id: Int,
        @Field("sets") sets: Int,
        @Field("reps") reps: Int,
        @Field("weight") weight: Int
    ): Call<LoginResponse>

    @FormUrlEncoded
    @PUT("api/calories/{user_id}")
    fun updateCalories(
        @Path("user_id") id: Int,
        @Field("maintenance") maintenance: String,
        @Field("surplus") surplus: String,
        @Field("deficit") deficit: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @PUT("api/update-weights/{user_id}")
    fun updateWeight(
        @Path("user_id") id: Int,
        @Field("weight") weight: String,
        @Field("goal_weight") goal_weight: String,
    ): Call<LoginResponse>

    @DELETE("api/ProgramExercises/{id}")
    fun deleteProgramExercise(@Path("id") id: Int): Call<LoginResponse>

    @FormUrlEncoded
    @POST("api/ProgramExercises")
    fun exerciseAdd(
        @Field("exercise") exercise: String,
        @Field("group_muscle") groupMuscle: String,
        @Field("target_muscle") targetMuscle: String,
        @Field("sets") sets: Int,
        @Field("reps") reps: Int,
        @Field("weight") weight: Int,
        @Field("environment") environment: String,
        @Field("program_goal") goal: String,
        @Field("proper_form") properForm: String,
        @Field("program_id") programId: Int
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("api/logout")
    fun userLogout(

    ):Call<LoginResponse>

    @GET("api/profile/{user_id}")
    fun getUserProfile(@Path("user_id") userId: Int): Call<ProfileResponse>

    @GET("api/calories/{user_id}")
    fun getUserCalories(@Path("user_id") userId: Int): Call<CaloriesResponse>

    @GET("api/program/{user_id}")
    fun getUserProgram(@Path("user_id") userId: Int): Call<ProgramResponse>

    @GET("api/exercise/{groupMuscle}")
    fun getExercises(@Path("groupMuscle") groupMuscle: String): Call<ExercisesResponse>

    @GET("api/ProgramExercises/{program_id}")
    fun getProgramExercises(@Path("program_id") programId: Int): Call<ProgramExercisesResponse>

    @PUT("api/progressive-overload/{program_id}")
    fun progressiveOverload(@Path("program_id") programId: Int): Call<Void>

    @FormUrlEncoded
    @POST("api/user/change-password")
    fun changePassword(
        @Header("Authorization") authorization: String,
        @Field("current_password") currentPassword: String?,
        @Field("new_password") newPassword: String?,
        @Field("confirm_new_password") confirmNewPassword: String?
    ): Call<DefaultResponse>

    @GET("api/top-exercises/{goal}")
    fun getTopExercisesByGoal(
        @Path("goal") goal: String): Call<TopExercisesResponse>

    @FormUrlEncoded
    @POST("api/exerciserequest")
    fun requestExercise(
        @Field("ExerciseName") ExerciseName: String?,
        @Field("GroupMuscle") GroupMuscle: String?,
        @Field("TargetMuscle") TargetMuscle: String?
    ): Call<DefaultResponse>
}
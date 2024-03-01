package com.example.gymrat.api

import com.example.gymrat.Models.DefaultResponse
import com.example.gymrat.Models.ExercisesResponse
import com.example.gymrat.Models.LoginResponse
import com.example.gymrat.Models.ProfileResponse
import com.example.gymrat.Models.ProgramExercisesResponse
import com.example.gymrat.Models.ProgramResponse
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
        @Field("profile[environment]") environemnt: String,
        @Field("profile[gender]") gender: String,
        @Field("program[title]") title: String,
        @Field("program[goal]") pgoal: String
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
        @Field("program_goal") goal: String,
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
}
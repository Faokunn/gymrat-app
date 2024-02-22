package com.example.gymrat.api

import com.example.gymrat.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.gymrat.Models.DefaultResponse
import com.example.gymrat.Models.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

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
}
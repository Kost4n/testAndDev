package com.kost4n.testanddev.api

import com.kost4n.testanddev.api.data.Avatar
import retrofit2.Call
import retrofit2.http.*

interface UsersApi {
    @POST("/api/v1/users/send-auth-code")
    fun sendCode(
        @Body phone: String
    ): Call<ResponseSendCode>

    @POST("/api/v1/users/check-auth-code")
    fun checkCode(
        @Body phone: String,
        @Body code: String
    ): Call<ResponseCheckCode>


    @POST("/api/v1/users/register")
    fun register(
        @Body phone: String,
        @Body name: String,
        @Body username: String
    ): Call<ResponseRegistration>

    @GET("/api/v1/users/me")
    fun getCurrentUser(
        @Header("Authorization") accessToken: String,
    ): Call<ResponseGetCurrentUser>

    @PUT("/api/v1/users/me")
    fun updateUser(
        @Header("Authorization") accessToken: String,
        @Body birthday: String,
        @Body city: String,
    ): Call<ResponseUpdateUser>

    @POST("/api/v1/users/refresh-token")
    fun refreshToken(
        @Header("Authorization") accessToken: String,
        @Body refreshToken: String
    ): Call<ResponseRefreshToken>

    @GET("/api/v1/users/check-jwt")
    fun checkJWT(
        @Header("Authorization") accessToken: String
    ): Call<ResponseCheckJwt>
}
package com.kost4n.testanddev.api

object Common {
    private const val BASE_URL = " https://planerok.ru"
    val retrofit: UsersApi
        get() = RetrofitClient.getClient(BASE_URL).create(UsersApi::class.java)
}
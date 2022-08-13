package com.picpay.desafio.android.data.services

import com.picpay.desafio.android.data.remote.UserResponse
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}
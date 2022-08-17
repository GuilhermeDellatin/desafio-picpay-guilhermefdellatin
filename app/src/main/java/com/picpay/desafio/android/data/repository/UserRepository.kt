package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUsers(): Flow<List<User>>
    suspend fun listUser(): List<User>
    suspend fun save(user: List<User>)

}
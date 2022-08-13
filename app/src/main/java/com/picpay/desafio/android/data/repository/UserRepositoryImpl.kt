package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.remote.toUserModel
import com.picpay.desafio.android.data.services.PicPayService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.Exception

class UserRepositoryImpl(
    private val service: PicPayService
) : UserRepository {

    override suspend fun getUsers() = flow {
        try {
            val userList = service.getUsers().map {
                it.toUserModel()
            }
            emit(userList)
        } catch (e: HttpException) {
            throw RemoteException("Unable to connect Api")
        }
    }
}

class RemoteException(message: String) : Exception(message)
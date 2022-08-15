package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.database.AppDataBase
import com.picpay.desafio.android.data.mapper.toUserEntity
import com.picpay.desafio.android.data.mapper.toUserModel
import com.picpay.desafio.android.data.mapper.toUsersModel
import com.picpay.desafio.android.data.services.PicPayService
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class UserRepositoryImpl(
    private val service: PicPayService,
    appDataBase: AppDataBase
) : UserRepository {

    private val usersDao = appDataBase.usersDao()

    override suspend fun getUsers() = flow {
        try {
            val userList = service.getUsers().map {
                it.toUserModel()
            }
            save(userList)
            emit(userList)
        }
        catch (e: Exception) {
            try {
                emit(listUser())
            } catch (e: HttpException) {
                throw RemoteException("Unable to connect Api")
            }
        }

    }

    private suspend fun listUser(): List<User> {
        return usersDao.getAllUsers().map {
            it.toUsersModel()
        }
    }

    private suspend fun save(user: List<User>) {
        usersDao.insertAllUsers(
            user.map {
                it.toUserEntity()
            }
        )
    }

}


class RemoteException(message: String) : Exception(message)
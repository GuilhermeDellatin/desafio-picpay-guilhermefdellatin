package com.picpay.desafio.android.data.repository

import android.util.Log
import com.picpay.desafio.android.commons.constants.DataBaseConstants
import com.picpay.desafio.android.commons.exceptions.RemoteException
import com.picpay.desafio.android.data.local.database.AppDataBase
import com.picpay.desafio.android.data.mapper.toUserEntity
import com.picpay.desafio.android.data.mapper.toUserModel
import com.picpay.desafio.android.data.mapper.toUsersModel
import com.picpay.desafio.android.data.services.PicPayService
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.flow

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
                if (listUser().isNotEmpty()) {
                    emit(listUser())
                } else {
                    Log.e(DataBaseConstants.DATA_BASE_ERROR, DataBaseConstants.DATA_BASE_ERROR_GET_USERS)
                    throw RemoteException("Unable to connect Api")
                }
        }

    }

    override suspend fun listUser(): List<User> {
        return usersDao.getAllUsers().map {
            it.toUsersModel()
        }
    }

    override suspend fun save(user: List<User>) {
        usersDao.insertAllUsers(
            user.map {
                it.toUserEntity()
            }
        )
    }

}
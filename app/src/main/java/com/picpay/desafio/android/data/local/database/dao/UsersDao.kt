package com.picpay.desafio.android.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.constants.DataBaseConstants
import com.picpay.desafio.android.data.local.database.entity.UserEntity

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(usersEntity: List<UserEntity>)

    @Query("SELECT * FROM ${DataBaseConstants.USERS_TABLE_NAME} ORDER BY id ASC")
    suspend fun getAllUsers(): List<UserEntity>
}
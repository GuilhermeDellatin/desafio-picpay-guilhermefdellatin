package com.picpay.desafio.android.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.constants.DataBaseConstants.APP_DATABASE_NAME
import com.picpay.desafio.android.data.local.database.dao.UsersDao
import com.picpay.desafio.android.data.local.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun usersDao(): UsersDao

    companion object {
        fun getInstance(context: Context): AppDataBase {
            return Room
                .databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    APP_DATABASE_NAME
                )
                .build()
        }
    }
}
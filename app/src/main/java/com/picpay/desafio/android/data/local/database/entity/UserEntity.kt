package com.picpay.desafio.android.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.constants.DataBaseConstants
import com.picpay.desafio.android.domain.model.User

@Entity(tableName = DataBaseConstants.USERS_TABLE_NAME)
data class UserEntity(

    @ColumnInfo(name = DataBaseConstants.USERS_COLUMN_INFO_IMG)
    val img: String,
    @ColumnInfo(name = DataBaseConstants.USERS_COLUMN_INFO_NAME)
    val name: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = DataBaseConstants.USERS_COLUMN_INFO_ID)
    val id: Int,
    @ColumnInfo(name = DataBaseConstants.USERS_COLUMN_INFO_USERNAME)
    val username: String,
)
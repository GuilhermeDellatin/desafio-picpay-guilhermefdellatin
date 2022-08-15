package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.local.database.entity.UserEntity
import com.picpay.desafio.android.data.remote.UserResponse
import com.picpay.desafio.android.domain.model.User

fun UserEntity.toUsersModel(): User {
    return User(
        img = this.img,
        name = this.name,
        id = this.id,
        username = this.username
    )
}

fun UserResponse.toUserModel(): User {
    return User(
        img = this.img,
        name = this.name,
        id = this.id,
        username = this.username
    )
}

fun User.toUserEntity() = UserEntity(img, name, id, username)
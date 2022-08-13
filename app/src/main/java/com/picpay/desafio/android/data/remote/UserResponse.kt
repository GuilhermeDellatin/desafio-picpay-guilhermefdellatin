package com.picpay.desafio.android.data.remote

import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.domain.model.User

data class UserResponse(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
)

fun UserResponse.toUserModel(): User {
    return User(
        img = this.img,
        name = this.name,
        id = this.id,
        username = this.username
    )
}
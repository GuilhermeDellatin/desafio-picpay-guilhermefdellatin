package com.picpay.desafio.android.factory

import com.picpay.desafio.android.domain.model.User

class UserFactory {

    fun createLisOfUser() = listOf(
        User(img = "img1", name = "name1", id = 1, username = "username1"),
        User(img = "img2", name = "name2", id = 2, username = "username2")
    )
}
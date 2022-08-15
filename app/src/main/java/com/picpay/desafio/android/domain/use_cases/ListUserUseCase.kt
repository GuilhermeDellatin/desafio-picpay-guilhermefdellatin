package com.picpay.desafio.android.domain.use_cases

import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.use_cases.base.UseCase
import kotlinx.coroutines.flow.Flow

class ListUserUseCase(
    private val repository: UserRepository
) : UseCase.NoParam<List<User>>() {

    override suspend fun execute(): Flow<List<User>> {
        return repository.getUsers()
    }

}
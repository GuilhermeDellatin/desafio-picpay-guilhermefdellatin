package com.picpay.desafio.android.domain.use_cases

import com.picpay.desafio.android.commons.exceptions.RemoteException
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.factory.UserFactory
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ListUserUseCaseTest {

    private val repository: UserRepository = mockk()

    private lateinit var listUserUseCase: ListUserUseCase

    private val users = UserFactory().createLisOfUser()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        listUserUseCase = ListUserUseCase(repository)
    }

    @After
    fun tearDown() = unmockkAll()

    @Test
    fun `Given that UseCase return user list from api when call success`() = runBlocking {
        coEvery { repository.getUsers() } returns flowOf(users)

        val result = listUserUseCase.invoke()
        val expected = users
        assertEquals(result.first(), expected)
        assertEquals(result.last(), expected)
    }

    @Test
    fun `Given that return an exception when call api fails`(): Unit = runBlocking {
        val errorExpected = flow<List<User>> { throw RemoteException("error") }

        coEvery { repository.getUsers() } returns errorExpected
        listUserUseCase.invoke().catch { error ->
            assertEquals(errorExpected.last(), error.message)
        }
    }

}
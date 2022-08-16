package com.picpay.desafio.android.ui.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.commons.exceptions.RemoteException
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.use_cases.ListUserUseCase
import com.picpay.desafio.android.factory.UserFactory
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class UserViewModelTest {

    private val listUserUseCase: ListUserUseCase = mockk()

    private lateinit var userViewModel: UserViewModel

    private val users = UserFactory().createLisOfUser()

    private val mainTestDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(mainTestDispatcher)
        userViewModel = UserViewModel(listUserUseCase)
    }

    @After
    fun tearDown() = unmockkAll()

    @Test
    fun `Given that notify Success from ViewState when get users return success`() = runBlocking {
        coEvery { listUserUseCase.invoke() } returns flowOf(users)

        val expected = ViewState.Success<User>(users)

        userViewModel.getUsersList()

        assertEquals(expected, userViewModel.state.value)

    }

    @Test
    fun `Given that notify Loading from ViewState when get users loading`() = runBlocking {

        coEvery { listUserUseCase.invoke() } returns flow { users }

        val expected = ViewState.Loading

        userViewModel.getUsersList()

        assertEquals(expected, userViewModel.state.value)

    }

}
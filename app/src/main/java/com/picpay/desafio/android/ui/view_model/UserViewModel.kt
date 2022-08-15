package com.picpay.desafio.android.ui.view_model

import androidx.lifecycle.*
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.use_cases.ListUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class UserViewModel(
    private val listUserUseCase: ListUserUseCase
) : ViewModel() {

    private val _state = MutableLiveData<ViewState<List<User>>>()
    val state: LiveData<ViewState<List<User>>> get() = _state

    init {
        getUsersList()
    }

    fun getUsersList() {
        viewModelScope.launch {
            listUserUseCase()
                .flowOn(Dispatchers.Main)
                .onStart {
                    _state.postValue(ViewState.Loading)
                }
                .catch {
                    _state.postValue(ViewState.Error(it))
                }
                .collect {
                    _state.postValue(ViewState.Success(it))
                }
        }
    }

}
package com.picpay.desafio.android.ui.view_model

import com.picpay.desafio.android.domain.model.User

sealed class ViewState<out T: Any>  {

    object Loading : ViewState<Nothing>()

    data class Success<out T: Any>(val result: List<User>) : ViewState<T>()

    data class Error(val error: Throwable) : ViewState<Nothing>()
}
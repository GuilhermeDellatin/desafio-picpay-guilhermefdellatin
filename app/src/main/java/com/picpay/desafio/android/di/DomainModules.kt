package com.picpay.desafio.android.di

import com.picpay.desafio.android.domain.use_cases.ListUserUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModules {

    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule() : Module {
        return module {
            factory { ListUserUseCase(get()) }
        }
    }
}
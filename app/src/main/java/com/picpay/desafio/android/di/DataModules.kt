package com.picpay.desafio.android.di

import android.util.Log
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.database.AppDataBase
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.data.services.PicPayService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModules {

    private const val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    private const val HTTP_TAG = "OkHttp"

    fun load() {
        loadKoinModules(networkingModule() + repositoryModules() + databaseModules())
    }

    private fun networkingModule(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(HTTP_TAG, ": $it")
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<PicPayService>(get(), get())
            }
        }
    }

    private fun repositoryModules() : Module {
        return module {
            single<UserRepository> { UserRepositoryImpl(get(), get()) }
        }
    }

    private fun databaseModules() : Module {
        return module {
            single { AppDataBase.getInstance(androidApplication()) }
        }
    }

    private inline fun <reified T> createService(client: OkHttpClient, factory: GsonConverterFactory): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(factory)
            .build().create(T::class.java)
    }
}
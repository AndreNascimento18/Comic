package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.network.ComicsApi
import com.example.data.network.Repository
import com.example.data.network.RepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

val dataModule = module {
    factory {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .connectTimeout(30L, TimeUnit.SECONDS)
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Timber.tag("API").d(message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.build()
    }

    single <ComicsApi> {
        val client = get<OkHttpClient>()
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(ComicsApi::class.java)
    }

    single<Repository> {
        RepositoryImpl(get())
    }
}
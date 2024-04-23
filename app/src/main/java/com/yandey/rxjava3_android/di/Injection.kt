package com.yandey.rxjava3_android.di

import android.content.Context
import com.yandey.rxjava3_android.data.remote.network.ApiConfig
import com.yandey.rxjava3_android.data.repository.QuoteRepository
import com.yandey.rxjava3_android.data.repository.QuoteRepositoryImpl

object Injection {
    fun provideRepository(context: Context): QuoteRepository {
        val apiService = ApiConfig.provideApiService(context.cacheDir, 10 * 1024 * 1024)
        return QuoteRepositoryImpl.getInstance(apiService)
    }
}
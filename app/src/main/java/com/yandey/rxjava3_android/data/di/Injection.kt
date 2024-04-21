package com.yandey.rxjava3_android.data.di

import android.content.Context
import com.yandey.rxjava3_android.data.remote.network.ApiConfig
import com.yandey.rxjava3_android.data.repository.TaskRepository
import com.yandey.rxjava3_android.data.repository.TaskRepositoryImpl

object Injection {
    fun provideRepository(context: Context): TaskRepository {
        val apiService = ApiConfig.provideApiService(context.cacheDir, 10 * 1024 * 1024)
        return TaskRepositoryImpl.getInstance(apiService)
    }
}
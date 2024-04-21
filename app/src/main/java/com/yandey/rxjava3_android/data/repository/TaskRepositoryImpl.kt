package com.yandey.rxjava3_android.data.repository

import com.yandey.rxjava3_android.data.remote.network.ApiService
import com.yandey.rxjava3_android.data.remote.response.get_tasks.TaskResponse
import io.reactivex.rxjava3.core.Single

class TaskRepositoryImpl(
    private val apiService: ApiService
) : TaskRepository {
    override fun getAllTask(): Single<TaskResponse> {
        return apiService.getAllTask()
    }

    companion object {
        @Volatile
        private var INSTANCE: TaskRepository? = null

        fun getInstance(apiService: ApiService): TaskRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: TaskRepositoryImpl(apiService)
            }.also { INSTANCE = it }
        }
    }
}
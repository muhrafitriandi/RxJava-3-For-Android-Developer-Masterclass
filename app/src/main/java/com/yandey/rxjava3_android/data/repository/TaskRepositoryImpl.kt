package com.yandey.rxjava3_android.data.repository

import android.annotation.SuppressLint
import com.yandey.rxjava3_android.data.remote.mock.mockTaskResponse
import com.yandey.rxjava3_android.data.remote.network.ApiService
import com.yandey.rxjava3_android.data.remote.response.add_task.AddTaskBody
import com.yandey.rxjava3_android.data.remote.response.get_tasks.TaskResponse
import com.yandey.rxjava3_android.data.remote.response.get_tasks.TaskResponseItem
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.text.SimpleDateFormat
import java.util.Date

class TaskRepositoryImpl(
    private val apiService: ApiService
) : TaskRepository {
    override fun getAllTask(): Single<TaskResponse> {
        return apiService.getAllTask()
    }

    @SuppressLint("SimpleDateFormat")
    override fun addTask(request: AddTaskBody): Completable {
        val (note, userId, title, body, status) = request

        mockTaskResponse.taskResponse.add(
            TaskResponseItem(
                note = note,
                updatedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(Date()),
                userId = userId,
                createdAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(Date()),
                id = (1..10_000).random(),
                title = title,
                body = body,
                status = status
            )
        )
        return apiService.addTask(request)
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
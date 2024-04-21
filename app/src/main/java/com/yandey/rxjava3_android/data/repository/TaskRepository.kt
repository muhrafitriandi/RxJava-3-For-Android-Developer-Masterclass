package com.yandey.rxjava3_android.data.repository

import com.yandey.rxjava3_android.data.remote.response.get_tasks.TaskResponse
import io.reactivex.rxjava3.core.Single

interface TaskRepository {
    fun getAllTask(): Single<TaskResponse>
}
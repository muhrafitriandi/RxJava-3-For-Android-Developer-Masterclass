package com.yandey.rxjava3_android.data.repository

import com.yandey.rxjava3_android.data.remote.response.add_task.AddTaskBody
import com.yandey.rxjava3_android.data.remote.response.delete_task.DeleteTaskBody
import com.yandey.rxjava3_android.data.remote.response.edit_task.EditTaskBody
import com.yandey.rxjava3_android.data.remote.response.get_tasks.TaskResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface TaskRepository {
    fun getAllTask(): Single<TaskResponse>
    fun addTask(request: AddTaskBody): Completable
    fun editTask(request: EditTaskBody): Completable
    fun deleteTask(request: DeleteTaskBody): Completable
    fun searchTask(query: String): Single<TaskResponse>
}
package com.yandey.rxjava3_android.data.repository

import android.annotation.SuppressLint
import com.yandey.rxjava3_android.data.remote.mock.mockTaskResponse
import com.yandey.rxjava3_android.data.remote.network.ApiService
import com.yandey.rxjava3_android.data.remote.response.add_task.AddTaskBody
import com.yandey.rxjava3_android.data.remote.response.delete_task.DeleteTaskBody
import com.yandey.rxjava3_android.data.remote.response.edit_task.EditTaskBody
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
                id = mockTaskResponse.taskResponse.size + 1,
                title = title,
                body = body,
                status = status
            )
        )
        return apiService.addTask(request)
    }

    override fun editTask(request: EditTaskBody): Completable {
        val editedItem = mockTaskResponse.taskResponse.find { it.id == request.taskId }
        val (note, _, userId, title, body, status) = request

        if (editedItem != null) {
            val updatedItem = editedItem.copy(note = note, userId = userId, title = title, body = body, status = status)
            mockTaskResponse.taskResponse.replaceAll { if (it.id == request.taskId) updatedItem else it }
        }

        return apiService.editTask(request)
    }

    override fun deleteTask(request: DeleteTaskBody): Completable {
        val deletedItem = mockTaskResponse.taskResponse.find { it.id == request.taskId && it.userId == request.userId }
        mockTaskResponse.taskResponse.remove(deletedItem)

        return apiService.deleteTask(request)
    }

    override fun searchTask(query: String): Single<TaskResponse> {
        return apiService.searchTask(query)
            .map { response ->
                val filteredTasks = response.taskResponse.filter { task ->
                    task.title.contains(query)
                }.toMutableList()
                TaskResponse(filteredTasks)
            }
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
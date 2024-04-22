package com.yandey.rxjava3_android.data.remote.network

import com.yandey.rxjava3_android.data.remote.EndPoint
import com.yandey.rxjava3_android.data.remote.response.add_task.AddTaskBody
import com.yandey.rxjava3_android.data.remote.response.delete_task.DeleteTaskBody
import com.yandey.rxjava3_android.data.remote.response.edit_task.EditTaskBody
import com.yandey.rxjava3_android.data.remote.response.get_tasks.TaskResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    @Headers(EndPoint.HEADER_ACCEPT)
    @GET(EndPoint.GET_ALL_TASK)
    fun getAllTask(): Single<TaskResponse>

    @Headers(EndPoint.HEADER_ACCEPT)
    @POST(EndPoint.ADD_TASK)
    fun addTask(@Body request: AddTaskBody): Completable

    @Headers(EndPoint.HEADER_ACCEPT)
    @PUT(EndPoint.EDIT_TASK)
    fun editTask(@Body request: EditTaskBody): Completable

    @Headers(EndPoint.HEADER_ACCEPT)
    @POST(EndPoint.DELETE_TASK)
    fun deleteTask(@Body request: DeleteTaskBody): Completable
}
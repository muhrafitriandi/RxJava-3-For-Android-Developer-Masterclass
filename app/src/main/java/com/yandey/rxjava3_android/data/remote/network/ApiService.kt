package com.yandey.rxjava3_android.data.remote.network

import com.yandey.rxjava3_android.data.remote.EndPoint
import com.yandey.rxjava3_android.data.remote.response.get_tasks.TaskResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers(EndPoint.HEADER_ACCEPT)
    @GET(EndPoint.GET_ALL_TASK)
    fun getListRestaurant(): Single<TaskResponse>
}
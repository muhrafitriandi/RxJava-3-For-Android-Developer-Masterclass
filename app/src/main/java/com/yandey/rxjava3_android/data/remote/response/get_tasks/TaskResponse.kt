package com.yandey.rxjava3_android.data.remote.response.get_tasks

import com.google.gson.annotations.SerializedName

data class TaskResponse(

    @field:SerializedName("TaskResponse")
    val taskResponse: List<TaskResponseItem>
)
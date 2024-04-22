package com.yandey.rxjava3_android.data.remote.response.delete_task

import com.google.gson.annotations.SerializedName

data class DeleteTaskBody(
    @field:SerializedName("task_id")
    val taskId: Int,

    @field:SerializedName("user_id")
    val userId: Int,
)
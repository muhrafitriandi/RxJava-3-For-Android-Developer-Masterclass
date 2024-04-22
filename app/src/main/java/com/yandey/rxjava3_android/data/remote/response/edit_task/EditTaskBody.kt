package com.yandey.rxjava3_android.data.remote.response.edit_task

import com.google.gson.annotations.SerializedName

data class EditTaskBody(

    @field:SerializedName("note")
    val note: String,

    @field:SerializedName("task_id")
    val taskId: Int,

    @field:SerializedName("user_id")
    val userId: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("body")
    val body: String,

    @field:SerializedName("status")
    val status: String
)

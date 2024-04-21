package com.yandey.rxjava3_android.data.remote.response.get_tasks

import com.google.gson.annotations.SerializedName

data class TaskResponseItem(

    @field:SerializedName("note")
    val note: String,

    @field:SerializedName("updated_at")
    val updatedAt: String,

    @field:SerializedName("user_id")
    val userId: Int,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("body")
    val body: String,

    @field:SerializedName("status")
    val status: String
)
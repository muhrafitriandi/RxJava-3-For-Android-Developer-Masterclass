package com.yandey.rxjava3_android.data.remote.response.add_task

import com.google.gson.annotations.SerializedName

data class AddTaskBody(

    @field:SerializedName("note")
    val note: String,

    @field:SerializedName("user_id")
    val userId: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("body")
    val body: String,

    @field:SerializedName("status")
    val status: String
)
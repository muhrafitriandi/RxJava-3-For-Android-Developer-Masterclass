package com.yandey.rxjava3_android.data.remote.mock

import com.yandey.rxjava3_android.data.remote.response.get_tasks.TaskResponse
import com.yandey.rxjava3_android.data.remote.response.get_tasks.TaskResponseItem

val mockTaskResponse = TaskResponse(
    taskResponse = listOf(
        TaskResponseItem(
            note = "Note 1",
            updatedAt = "2024-04-21T08:00:00Z",
            userId = 1,
            createdAt = "2024-04-20T12:00:00Z",
            id = 1,
            title = "Task Title 1",
            body = "Task Body 1",
            status = "pending"
        ),
        TaskResponseItem(
            note = "Note 2",
            updatedAt = "2024-04-21T08:30:00Z",
            userId = 2,
            createdAt = "2024-04-20T12:30:00Z",
            id = 2,
            title = "Task Title 2",
            body = "Task Body 2",
            status = "completed"
        ),
        TaskResponseItem(
            note = "Note 3",
            updatedAt = "2024-04-21T09:00:00Z",
            userId = 3,
            createdAt = "2024-04-20T13:00:00Z",
            id = 3,
            title = "Task Title 3",
            body = "Task Body 3",
            status = "pending"
        ),
        TaskResponseItem(
            note = "Note 4",
            updatedAt = "2024-04-21T09:30:00Z",
            userId = 4,
            createdAt = "2024-04-20T13:30:00Z",
            id = 4,
            title = "Task Title 4",
            body = "Task Body 4",
            status = "in_progress"
        ),
        TaskResponseItem(
            note = "Note 5",
            updatedAt = "2024-04-21T10:00:00Z",
            userId = 5,
            createdAt = "2024-04-20T14:00:00Z",
            id = 5,
            title = "Task Title 5",
            body = "Task Body 5",
            status = "completed"
        ),
        TaskResponseItem(
            note = "Note 6",
            updatedAt = "2024-04-21T10:30:00Z",
            userId = 6,
            createdAt = "2024-04-20T14:30:00Z",
            id = 6,
            title = "Task Title 6",
            body = "Task Body 6",
            status = "pending"
        ),
        TaskResponseItem(
            note = "Note 7",
            updatedAt = "2024-04-21T11:00:00Z",
            userId = 7,
            createdAt = "2024-04-20T15:00:00Z",
            id = 7,
            title = "Task Title 7",
            body = "Task Body 7",
            status = "in_progress"
        ),
        TaskResponseItem(
            note = "Note 8",
            updatedAt = "2024-04-21T11:30:00Z",
            userId = 8,
            createdAt = "2024-04-20T15:30:00Z",
            id = 8,
            title = "Task Title 8",
            body = "Task Body 8",
            status = "completed"
        ),
        TaskResponseItem(
            note = "Note 9",
            updatedAt = "2024-04-21T12:00:00Z",
            userId = 9,
            createdAt = "2024-04-20T16:00:00Z",
            id = 9,
            title = "Task Title 9",
            body = "Task Body 9",
            status = "pending"
        ),
        TaskResponseItem(
            note = "Note 10",
            updatedAt = "2024-04-21T12:30:00Z",
            userId = 10,
            createdAt = "2024-04-20T16:30:00Z",
            id = 10,
            title = "Task Title 10",
            body = "Task Body 10",
            status = "in_progress"
        )
    )
)
package com.yandey.rxjava3_android.ui

import com.yandey.rxjava3_android.data.remote.response.get_tasks.TaskResponseItem

sealed class MainUiState {
    data object Loading : MainUiState()
    data class Success(val taskResponse: List<TaskResponseItem>) : MainUiState()
    data class Error(val message: String) : MainUiState()
}
package com.yandey.rxjava3_android.ui

import com.yandey.rxjava3_android.data.local.entity.StudentEntity

sealed class MainUiState {
    data object Loading : MainUiState()
    data class Success(
        val students: List<StudentEntity>
    ) : MainUiState()

    data class Error(val message: String) : MainUiState()
}
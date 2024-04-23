package com.yandey.rxjava3_android.ui

import androidx.paging.PagingData
import com.yandey.rxjava3_android.data.remote.response.QuoteResponseItem

sealed class MainUiState {
    data object Loading : MainUiState()
    data class Success(val quoteResponse: PagingData<QuoteResponseItem>) : MainUiState()
    data class Error(val message: String) : MainUiState()
}
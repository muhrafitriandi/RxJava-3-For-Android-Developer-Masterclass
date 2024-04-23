package com.yandey.rxjava3_android.data.repository

import com.yandey.rxjava3_android.data.remote.network.ApiService
import com.yandey.rxjava3_android.data.remote.response.QuoteResponseItem
import io.reactivex.rxjava3.core.Single

class QuoteRepositoryImpl(
    private val apiService: ApiService
) : QuoteRepository {
    override fun getQuote(): Single<List<QuoteResponseItem>> {
        return apiService.getQuote(1, 5)
    }

    companion object {
        @Volatile
        private var INSTANCE: QuoteRepository? = null

        fun getInstance(apiService: ApiService): QuoteRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: QuoteRepositoryImpl(apiService)
            }.also { INSTANCE = it }
        }
    }
}
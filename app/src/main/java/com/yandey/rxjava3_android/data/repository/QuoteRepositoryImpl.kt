package com.yandey.rxjava3_android.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.yandey.rxjava3_android.data.paging.QuotePagingSource
import com.yandey.rxjava3_android.data.remote.network.ApiService
import com.yandey.rxjava3_android.data.remote.response.QuoteResponseItem
import io.reactivex.rxjava3.core.Flowable

class QuoteRepositoryImpl(
    private val apiService: ApiService
) : QuoteRepository {
    override fun getQuote(): Flowable<PagingData<QuoteResponseItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 20,
                enablePlaceholders = false,
                initialLoadSize = 30,
                maxSize = 50
            ),
            pagingSourceFactory = { QuotePagingSource(apiService) }
        ).flowable
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
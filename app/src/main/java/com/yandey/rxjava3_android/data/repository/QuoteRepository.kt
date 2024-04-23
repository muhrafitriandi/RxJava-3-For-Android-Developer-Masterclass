package com.yandey.rxjava3_android.data.repository

import androidx.paging.PagingData
import com.yandey.rxjava3_android.data.remote.response.QuoteResponseItem
import io.reactivex.rxjava3.core.Flowable

interface QuoteRepository {
    fun getQuote(): Flowable<PagingData<QuoteResponseItem>>
}
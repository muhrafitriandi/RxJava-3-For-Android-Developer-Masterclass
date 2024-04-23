package com.yandey.rxjava3_android.data.repository

import com.yandey.rxjava3_android.data.remote.response.QuoteResponseItem
import io.reactivex.rxjava3.core.Single

interface QuoteRepository {
    fun getQuote(): Single<List<QuoteResponseItem>>
}
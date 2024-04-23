package com.yandey.rxjava3_android.data.remote.network

import com.yandey.rxjava3_android.data.EndPoint
import com.yandey.rxjava3_android.data.remote.response.QuoteResponseItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(EndPoint.GET_QUOTE)
    fun getQuote(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Single<List<QuoteResponseItem>>
}
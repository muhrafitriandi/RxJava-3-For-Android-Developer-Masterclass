package com.yandey.rxjava3_android.data.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.yandey.rxjava3_android.data.remote.network.ApiService
import com.yandey.rxjava3_android.data.remote.response.QuoteResponseItem
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class QuotePagingSource(
    private val apiService: ApiService
) : RxPagingSource<Int, QuoteResponseItem>() {
    override fun getRefreshKey(state: PagingState<Int, QuoteResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, QuoteResponseItem>> {
        val page = params.key ?: INITIAL_PAGE_INDEX
        val responseData = apiService.getQuote(page, params.loadSize)

        return responseData
            .map { response ->
                LoadResult.Page(
                    data = response,
                    prevKey = if (page == INITIAL_PAGE_INDEX) null else page - 1,
                    nextKey = if (response.isEmpty()) null else page + 1
                ) as LoadResult<Int, QuoteResponseItem>
            }
            .onErrorReturn { error ->
                LoadResult.Error(error)
            }
            .subscribeOn(Schedulers.io())
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}
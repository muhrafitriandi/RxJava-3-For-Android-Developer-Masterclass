package com.yandey.rxjava3_android.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.rxjava3.cachedIn
import com.yandey.rxjava3_android.base.BaseViewModel
import com.yandey.rxjava3_android.data.repository.QuoteRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainViewModel(
    private val quoteRepository: QuoteRepository
) : BaseViewModel<MainUiState>() {
    private val compositeDisposable = CompositeDisposable()

    init {
        getQuote()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getQuote() {
        uiState.value = MainUiState.Loading

        quoteRepository.getQuote().cachedIn(viewModelScope)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    uiState.value = MainUiState.Success(response)
                },
                { error ->
                    uiState.value = MainUiState.Error(error.message.toString())
                }
            ).also {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
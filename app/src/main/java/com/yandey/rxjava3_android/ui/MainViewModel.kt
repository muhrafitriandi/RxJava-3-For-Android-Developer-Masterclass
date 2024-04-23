package com.yandey.rxjava3_android.ui

import com.yandey.rxjava3_android.base.BaseViewModel
import com.yandey.rxjava3_android.data.repository.QuoteRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    private val quoteRepository: QuoteRepository
) : BaseViewModel<MainUiState>() {
    private val compositeDisposable = CompositeDisposable()

    init {
        getQuote()
    }

    private fun getQuote() {
        uiState.value = MainUiState.Loading

        quoteRepository.getQuote()
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
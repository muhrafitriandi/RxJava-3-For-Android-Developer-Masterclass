package com.yandey.rxjava3_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yandey.rxjava3_android.adapter.QuoteListAdapter
import com.yandey.rxjava3_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    private val quoteAdapter by lazy {
        QuoteListAdapter()
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.uiState().observe(this) { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        }

        initQuoteAdapter()
    }

    private fun render(uiState: MainUiState) {
        when (uiState) {
            is MainUiState.Error -> {
                // Show Error
                onError(uiState)
            }

            MainUiState.Loading -> {
                // Show Loading
                onLoading()
            }

            is MainUiState.Success -> {
                onSuccess(uiState)
            }
        }
    }

    private fun initQuoteAdapter() = with(binding) {
        with(rvQuote) {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = quoteAdapter
        }
    }

    private fun onSuccess(uiState: MainUiState.Success) = with(binding) {
        quoteAdapter.submitData(lifecycle, uiState.quoteResponse)
        rvQuote.post { rvQuote.scrollToPosition(0) }
    }

    private fun onLoading() {}

    private fun onError(uiState: MainUiState.Error) {}
}
package com.yandey.rxjava3_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.yandey.rxjava3_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.uiState().observe(this) { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        }
    }

    private fun render(uiState: MainUiState) {
        when (uiState) {
            is MainUiState.Error -> {
                // Do Something
            }
            MainUiState.Loading -> {
                // Do Something
            }
            is MainUiState.Success -> {
                // Do Something
            }
        }
    }
}
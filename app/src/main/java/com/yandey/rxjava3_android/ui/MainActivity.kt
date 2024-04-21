package com.yandey.rxjava3_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yandey.rxjava3_android.adapter.TaskAdapter
import com.yandey.rxjava3_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    private val taskAdapter by lazy {
        TaskAdapter()
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

        initTaskAdapter()
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

    private fun initTaskAdapter() = with(binding) {
        with(rvTask) {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }
}
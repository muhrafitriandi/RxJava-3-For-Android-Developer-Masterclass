package com.yandey.rxjava3_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yandey.rxjava3_android.R
import com.yandey.rxjava3_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainViewModel.uiState().observe(this) { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        }

        startOnClickListener()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab_add_student -> showDialog()
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

    private fun showDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_student, null)

        MaterialAlertDialogBuilder(this)
            .setTitle("Add New Student")
            .setMessage("Please enter the student's information below.")
            .setView(dialogView)
            .setNeutralButton("Cancel") { dialog, _ ->
                // Respond to neutral button press
                dialog.dismiss()
            }
            .setPositiveButton("Save") { _, _ ->
                // Respond to positive button press
            }
            .show()
    }

    private fun startOnClickListener() = with(binding) {
        fabAddStudent.setOnClickListener(this@MainActivity)
    }
}
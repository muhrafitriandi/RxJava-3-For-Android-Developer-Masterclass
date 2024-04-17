package com.yandey.rxjava3_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yandey.rxjava3_android.R
import com.yandey.rxjava3_android.data.local.entity.StudentEntity
import com.yandey.rxjava3_android.databinding.ActivityMainBinding
import com.yandey.rxjava3_android.databinding.DialogAddStudentBinding

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

    private fun startOnClickListener() = with(binding) {
        fabAddStudent.setOnClickListener(this@MainActivity)
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
        val dialogBinding = DialogAddStudentBinding.inflate(layoutInflater)

        MaterialAlertDialogBuilder(this@MainActivity)
            .setTitle("Add New Student")
            .setMessage("Please enter the student's information below.")
            .setView(dialogBinding.root)
            .setNeutralButton("Cancel") { dialog, _ ->
                // Respond to neutral button press
                dialog.dismiss()
            }
            .setPositiveButton("Save") { _, _ ->
                mainViewModel.insertStudent(
                    StudentEntity(
                        name = dialogBinding.etName.text.toString(),
                        age = dialogBinding.etAge.text.toString().toInt(),
                        subject = dialogBinding.etSubject.text.toString()
                    )
                )
            }
            .show()
    }
}
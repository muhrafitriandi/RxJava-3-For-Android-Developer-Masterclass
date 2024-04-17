package com.yandey.rxjava3_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yandey.rxjava3_android.R
import com.yandey.rxjava3_android.adapter.StudentAdapter
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

    private val studentAdapter by lazy {
        StudentAdapter {
            upsertDialog(true, it)
        }
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(this)
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
        initStudentAdapter()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab_add_student -> upsertDialog()
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
                onSuccess(uiState)
            }
        }
    }

    private fun upsertDialog(isUpdate: Boolean = false, existingStudent: StudentEntity? = null) {
        val dialogBinding = DialogAddStudentBinding.inflate(layoutInflater)

        if (isUpdate && existingStudent != null) {
            with(dialogBinding) {
                etName.setText(existingStudent.name)
                etAge.setText(existingStudent.age.toString())
                etSubject.setText(existingStudent.subject)
            }
        }

        MaterialAlertDialogBuilder(this@MainActivity)
            .setTitle(if (isUpdate) "Update Student" else "Add New Student")
            .setMessage("Please enter the student's information below.")
            .setView(dialogBinding.root)
            .setNeutralButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(if (isUpdate) "Update" else "Save") { _, _ ->
                val name = dialogBinding.etName.text.toString()
                val age = dialogBinding.etAge.text.toString().toIntOrNull() ?: 0
                val subject = dialogBinding.etSubject.text.toString()

                if (isUpdate && existingStudent != null) {
                    mainViewModel.updateStudent(existingStudent.copy(name = name, age = age, subject = subject))
                } else {
                    mainViewModel.insertStudent(StudentEntity(name = name, age = age, subject = subject))
                }
            }
            .show()
    }

    private fun initStudentAdapter() = with(binding) {
        with(rvStudent) {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = studentAdapter
        }
    }

    private fun onSuccess(uiState: MainUiState.Success) {
        studentAdapter.submitList(uiState.students)
    }
}
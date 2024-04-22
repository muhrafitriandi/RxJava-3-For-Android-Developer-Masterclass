package com.yandey.rxjava3_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yandey.rxjava3_android.R
import com.yandey.rxjava3_android.adapter.TaskAdapter
import com.yandey.rxjava3_android.data.remote.response.add_task.AddTaskBody
import com.yandey.rxjava3_android.databinding.ActivityMainBinding
import com.yandey.rxjava3_android.databinding.DialogAddTaskBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
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
        startOnClickListener()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab_add_task -> showAddDialog()
        }
    }

    private fun startOnClickListener() = with(binding) {
        fabAddTask.setOnClickListener(this@MainActivity)
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

    private fun showAddDialog() {
        val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)

        MaterialAlertDialogBuilder(this@MainActivity)
            .setTitle("Add Task")
            .setMessage("Please enter the task's information below.")
            .setView(dialogBinding.root)
            .setNeutralButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Save") { _, _ ->
                val title = dialogBinding.etTitle.text.toString()
                val body = dialogBinding.etBody.text.toString()
                val note = dialogBinding.etNote.text.toString()
                val status = dialogBinding.etStatus.text.toString()

                viewModel.addTask(
                    AddTaskBody(
                        userId = (1..10).random(),
                        title = title,
                        body = body,
                        note = note,
                        status = status
                    )
                )
            }
            .show()
    }

    private fun initTaskAdapter() = with(binding) {
        with(rvTask) {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    private fun onSuccess(uiState: MainUiState.Success) {
        taskAdapter.submitList(uiState.taskResponse)
    }
}
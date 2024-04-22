package com.yandey.rxjava3_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yandey.rxjava3_android.R
import com.yandey.rxjava3_android.adapter.TaskAdapter
import com.yandey.rxjava3_android.data.remote.response.add_task.AddTaskBody
import com.yandey.rxjava3_android.data.remote.response.edit_task.EditTaskBody
import com.yandey.rxjava3_android.data.remote.response.get_tasks.TaskResponseItem
import com.yandey.rxjava3_android.databinding.ActivityMainBinding
import com.yandey.rxjava3_android.databinding.DialogAddTaskBinding
import com.yandey.rxjava3_android.util.showDialog

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
            R.id.fab_add_task -> upsertDialog()
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

    private fun upsertDialog(isUpdate: Boolean = false, existingTask: TaskResponseItem? = null) {
        val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)

        if (isUpdate && existingTask != null) {
            dialogBinding.etTitle.setText(existingTask.title)
            dialogBinding.etBody.setText(existingTask.body)
            dialogBinding.etNote.setText(existingTask.note)
            dialogBinding.etStatus.setText(existingTask.status)
        }

        this.showDialog(
            title = if (isUpdate) "Update Task" else "Add Task",
            message = "Please enter the task's information below.",
            view = dialogBinding.root,
            neutralButtonText = "Cancel",
            onNeutralAction = { it.dismiss() },
            positiveButtonText = if (isUpdate) "Update" else "Save",
            onPositiveAction = {
                val title = dialogBinding.etTitle.text.toString()
                val body = dialogBinding.etBody.text.toString()
                val note = dialogBinding.etNote.text.toString()
                val status = dialogBinding.etStatus.text.toString()

                if (isUpdate && existingTask != null) {
                    viewModel.editTask(EditTaskBody(title = title, body = body, note = note, status = status, taskId = existingTask.id, userId = existingTask.id))
                } else {
                    viewModel.addTask(AddTaskBody(userId = (1..10).random(), title = title, body = body, note = note, status = status))
                }
            }
        )
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
package com.yandey.rxjava3_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.yandey.rxjava3_android.R
import com.yandey.rxjava3_android.adapter.TaskAdapter
import com.yandey.rxjava3_android.data.remote.response.add_task.AddTaskBody
import com.yandey.rxjava3_android.data.remote.response.delete_task.DeleteTaskBody
import com.yandey.rxjava3_android.data.remote.response.edit_task.EditTaskBody
import com.yandey.rxjava3_android.data.remote.response.get_tasks.TaskResponseItem
import com.yandey.rxjava3_android.databinding.ActivityMainBinding
import com.yandey.rxjava3_android.databinding.DialogAddTaskBinding
import com.yandey.rxjava3_android.util.setGone
import com.yandey.rxjava3_android.util.setVisible
import com.yandey.rxjava3_android.util.showDialog

class MainActivity : AppCompatActivity(), View.OnClickListener, OnQueryTextListener {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    private val taskAdapter by lazy {
        TaskAdapter(
            onEditListener = { upsertDialog(true, it) },
            onDeleteListener = { deleteDialog(it) }
        )
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            viewModel.searchTaskByName(it)
        }
        return true
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
                onError(uiState)
            }

            MainUiState.Loading -> {
                onLoad()
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
                    viewModel.editTask(EditTaskBody(title = title, body = body, note = note, status = status, taskId = existingTask.id, userId = existingTask.id)) {
                        upsertSuccessDialog()
                    }
                } else {
                    viewModel.addTask(AddTaskBody(userId = (1..10).random(), title = title, body = body, note = note, status = status)) {
                        upsertSuccessDialog()
                    }
                }
            }
        )
    }

    private fun deleteDialog(existingTask: TaskResponseItem) {
        this.showDialog(
            title = "Delete Task",
            message = "Are you sure want to delete `${existingTask.title}`?",
            neutralButtonText = "Cancel",
            onNeutralAction = { it.dismiss() },
            positiveButtonText = "Delete",
            onPositiveAction = {
                viewModel.deleteTask(DeleteTaskBody(taskId = existingTask.id, userId = existingTask.userId)) {
                    deletedSuccessDialog()
                }
            }
        )
    }

    private fun upsertSuccessDialog() {
        this@MainActivity.showDialog(
            title = "Great!",
            message = "Your data was saved successfully",
            positiveButtonText = "OK",
            onPositiveAction = { it.dismiss() }
        )
    }

    private fun deletedSuccessDialog() {
        this@MainActivity.showDialog(
            title = "Great!",
            message = "Your data was deleted successfully",
            positiveButtonText = "OK",
            onPositiveAction = { it.dismiss() }
        )
    }

    private fun initTaskAdapter() = with(binding) {
        with(rvTask) {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    private fun onSuccess(uiState: MainUiState.Success) = with(binding) {
        taskAdapter.submitList(uiState.taskResponse)
        rvTask.post { rvTask.scrollToPosition(0) }
        pbTask.setGone()
        rvTask.setVisible()
    }

    private fun onLoad() = with(binding) {
        pbTask.setVisible()
        rvTask.setGone()
    }

    private fun onError(uiState: MainUiState.Error) = with(binding) {
        pbTask.setGone()
        this@MainActivity.showDialog(
            title = "Error!",
            message = uiState.message,
            positiveButtonText = "OK",
            onPositiveAction = { it.dismiss() }
        )
    }
}
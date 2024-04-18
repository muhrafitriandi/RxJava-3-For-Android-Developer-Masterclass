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
import com.yandey.rxjava3_android.adapter.StudentAdapter
import com.yandey.rxjava3_android.data.local.entity.StudentEntity
import com.yandey.rxjava3_android.databinding.ActivityMainBinding
import com.yandey.rxjava3_android.databinding.DialogAddStudentBinding
import com.yandey.rxjava3_android.util.setGone
import com.yandey.rxjava3_android.util.setVisible
import com.yandey.rxjava3_android.util.showDialog

class MainActivity : AppCompatActivity(), View.OnClickListener, OnQueryTextListener {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    private val studentAdapter by lazy {
        StudentAdapter(
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

        mainViewModel.uiState().observe(this) { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        }

        startOnClickListener()
        initStudentAdapter()
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
        return false
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

    private fun upsertDialog(isUpdate: Boolean = false, existingStudent: StudentEntity? = null) {
        val dialogBinding = DialogAddStudentBinding.inflate(layoutInflater)

        if (isUpdate && existingStudent != null) {
            dialogBinding.etName.setText(existingStudent.name)
            dialogBinding.etAge.setText(existingStudent.age.toString())
            dialogBinding.etSubject.setText(existingStudent.subject)
        }

        this.showDialog(
            title = if (isUpdate) "Update Student" else "Add New Student",
            message = "Please enter the student's information below.",
            view = dialogBinding.root,
            neutralButtonText = "Cancel",
            onNeutralAction = { it.dismiss() },
            positiveButtonText = if (isUpdate) "Update" else "Save",
            onPositiveAction = {
                val name = dialogBinding.etName.text.toString()
                val age = dialogBinding.etAge.text.toString().toIntOrNull() ?: 0
                val subject = dialogBinding.etSubject.text.toString()

                if (isUpdate && existingStudent != null) {
                    mainViewModel.updateStudent(
                        existingStudent.copy(name = name, age = age, subject = subject)
                    ) { upsertSuccessDialog() }
                } else {
                    mainViewModel.insertStudent(
                        StudentEntity(name = name, age = age, subject = subject)
                    ) { upsertSuccessDialog() }
                }
            }
        )
    }

    private fun deleteDialog(existingStudent: StudentEntity) {
        this.showDialog(
            title = "Delete Student",
            message = "Are you sure want to delete `${existingStudent.name}`?",
            neutralButtonText = "Cancel",
            onNeutralAction = { it.dismiss() },
            positiveButtonText = "Delete",
            onPositiveAction = {
                mainViewModel.deleteStudent(existingStudent) {
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

    private fun initStudentAdapter() = with(binding) {
        with(rvStudent) {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = studentAdapter
        }
    }

    private fun onSuccess(uiState: MainUiState.Success) = with(binding) {
        studentAdapter.submitList(uiState.students)
        rvStudent.post { rvStudent.scrollToPosition(0) }
        pbStudent.setGone()
        rvStudent.setVisible()
    }

    private fun onLoad() = with(binding) {
        pbStudent.setVisible()
        rvStudent.setGone()
    }

    private fun onError(uiState: MainUiState.Error) = with(binding) {
        pbStudent.setGone()
        this@MainActivity.showDialog(
            title = "Error!",
            message = uiState.message,
            positiveButtonText = "OK",
            onPositiveAction = { it.dismiss() }
        )
    }
}
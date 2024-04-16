package com.yandey.rxjava3_android.ui

import com.yandey.rxjava3_android.base.BaseViewModel
import com.yandey.rxjava3_android.data.local.entity.StudentEntity
import com.yandey.rxjava3_android.data.repository.StudentRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    private val studentRepository: StudentRepository
) : BaseViewModel<MainUiState>() {
    private val compositeDisposable = CompositeDisposable()

    init {
        loadStudents()
    }

    fun insertStudent(studentEntity: StudentEntity) {
        uiState.value = MainUiState.Loading

        studentRepository.insert(studentEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    loadStudents()
                },
                { error ->
                    uiState.value = MainUiState.Error(error.message.toString())
                }
            ).also {
                compositeDisposable.add(it)
            }
    }

    fun updateStudent(studentEntity: StudentEntity) {
        uiState.value = MainUiState.Loading

        studentRepository.update(studentEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    loadStudents()
                },
                { error ->
                    uiState.value = MainUiState.Error(error.message.toString())
                }
            ).also {
                compositeDisposable.add(it)
            }
    }

    fun deleteStudent(studentEntity: StudentEntity) {
        uiState.value = MainUiState.Loading

        studentRepository.update(studentEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    loadStudents()
                },
                { error ->
                    uiState.value = MainUiState.Error(error.message.toString())
                }
            ).also {
                compositeDisposable.add(it)
            }
    }

    private fun loadStudents() {
        uiState.value = MainUiState.Loading

        studentRepository.getAllStudent()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { students ->
                    uiState.value = MainUiState.Success(students)
                },
                { error ->
                    uiState.value = MainUiState.Error(error.message.toString())
                }
            ).also {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
package com.yandey.rxjava3_android.ui

import com.yandey.rxjava3_android.base.BaseViewModel
import com.yandey.rxjava3_android.data.local.entity.StudentEntity
import com.yandey.rxjava3_android.data.repository.StudentRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MainViewModel(
    private val studentRepository: StudentRepository
) : BaseViewModel<MainUiState>() {
    private val compositeDisposable = CompositeDisposable()
    private val searchSubject = PublishSubject.create<String>()

    init {
        loadStudents()
        getStudentsByName()
    }

    fun insertStudent(
        studentEntity: StudentEntity,
        onInsertComplete: () -> Unit
    ) {
        uiState.value = MainUiState.Loading

        studentRepository.insert(studentEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onInsertComplete() },
                { error ->
                    uiState.value = MainUiState.Error(error.message.toString())
                }
            ).also {
                compositeDisposable.add(it)
            }
    }

    fun updateStudent(
        studentEntity: StudentEntity,
        onUpdateComplete: () -> Unit
    ) {
        uiState.value = MainUiState.Loading

        studentRepository.update(studentEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onUpdateComplete() },
                { error ->
                    uiState.value = MainUiState.Error(error.message.toString())
                }
            ).also {
                compositeDisposable.add(it)
            }
    }

    fun deleteStudent(
        studentEntity: StudentEntity,
        onDeleteComplete: () -> Unit
    ) {
        uiState.value = MainUiState.Loading

        studentRepository.delete(studentEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onDeleteComplete() },
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

    private fun getStudentsByName() {
        searchSubject
            .debounce(1, TimeUnit.SECONDS)
            .distinctUntilChanged()
            .doOnNext { uiState.postValue(MainUiState.Loading) }
            .flatMap { query ->
                studentRepository.getStudentsByName(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .toObservable()
            }
            .subscribe(
                { students ->
                    uiState.value = MainUiState.Success(students)
                },
                { error ->
                    uiState.value = MainUiState.Error(error.message.toString())
                }
            ).also { compositeDisposable.add(it) }
    }

    fun searchStudentByName(query: String) {
        searchSubject.onNext(query)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
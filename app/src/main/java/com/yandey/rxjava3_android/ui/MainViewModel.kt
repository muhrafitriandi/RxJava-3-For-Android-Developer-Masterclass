package com.yandey.rxjava3_android.ui

import com.yandey.rxjava3_android.base.BaseViewModel
import com.yandey.rxjava3_android.data.remote.response.add_task.AddTaskBody
import com.yandey.rxjava3_android.data.remote.response.delete_task.DeleteTaskBody
import com.yandey.rxjava3_android.data.remote.response.edit_task.EditTaskBody
import com.yandey.rxjava3_android.data.repository.TaskRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MainViewModel(
    private val taskRepository: TaskRepository
) : BaseViewModel<MainUiState>() {
    private val compositeDisposable = CompositeDisposable()
    private val searchSubject = PublishSubject.create<String>()

    init {
        getAllTask()
        getTaskByName()
    }

    private fun getAllTask() {
        uiState.value = MainUiState.Loading

        taskRepository.getAllTask()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    uiState.value = MainUiState.Success(response.taskResponse)
                },
                { error ->
                    uiState.value = MainUiState.Error(error.message.toString())
                }
            ).also {
                compositeDisposable.add(it)
            }
    }

    fun addTask(
        request: AddTaskBody,
        onAddComplete: () -> Unit
    ) {
        uiState.value = MainUiState.Loading

        taskRepository.addTask(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                getAllTask()
            }
            .subscribe(
                {
                    onAddComplete()
                },
                { error ->
                    uiState.value = MainUiState.Error(error.message.toString())
                }
            ).also {
                compositeDisposable.add(it)
            }
    }

    fun editTask(
        request: EditTaskBody,
        onEditComplete: () -> Unit
    ) {
        uiState.value = MainUiState.Loading

        taskRepository.editTask(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                getAllTask()
            }
            .subscribe(
                {
                    onEditComplete()
                },
                { error ->
                    uiState.value = MainUiState.Error(error.message.toString())
                }
            ).also {
                compositeDisposable.add(it)
            }
    }

    fun deleteTask(
        request: DeleteTaskBody,
        onDeleteComplete: () -> Unit
    ) {
        uiState.value = MainUiState.Loading

        taskRepository.deleteTask(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                getAllTask()
            }
            .subscribe(
                {
                    onDeleteComplete()
                },
                { error ->
                    uiState.value = MainUiState.Error(error.message.toString())
                }
            ).also {
                compositeDisposable.add(it)
            }
    }

    private fun getTaskByName() {
        searchSubject
            .debounce(1, TimeUnit.SECONDS)
            .distinctUntilChanged()
            .doOnNext { uiState.postValue(MainUiState.Loading) }
            .flatMap { query ->
                taskRepository.searchTask(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .toObservable()
            }
            .subscribe(
                { response ->
                    uiState.value = MainUiState.Success(response.taskResponse)
                },
                { error ->
                    uiState.value = MainUiState.Error(error.message.toString())
                }
            ).also { compositeDisposable.add(it) }
    }

    fun searchTaskByName(query: String) {
        uiState.value = MainUiState.Loading
        searchSubject.onNext(query)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
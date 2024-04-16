package com.yandey.rxjava3_android.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yandey.rxjava3_android.data.repository.StudentRepository
import com.yandey.rxjava3_android.di.Injection

class ViewModelFactory(
    private val studentRepository: StudentRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(studentRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(Injection.provideStudentRepository(context))
            }.also { INSTANCE = it }
        }
    }
}
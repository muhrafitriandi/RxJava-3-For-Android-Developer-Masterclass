package com.yandey.rxjava3_android.di

import android.content.Context
import com.yandey.rxjava3_android.data.local.db.DatabaseService
import com.yandey.rxjava3_android.data.repository.StudentRepository
import com.yandey.rxjava3_android.data.repository.StudentRepositoryImpl

object Injection {
    fun provideStudentRepository(context: Context): StudentRepository {
        val database = DatabaseService.getInstance(context)
        val dao = database.studentDao()

        return StudentRepositoryImpl.getInstance(dao)
    }
}
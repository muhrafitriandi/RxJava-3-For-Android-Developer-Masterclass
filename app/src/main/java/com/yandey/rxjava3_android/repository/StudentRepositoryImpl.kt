package com.yandey.rxjava3_android.repository

import com.yandey.rxjava3_android.local.dao.StudentDao
import com.yandey.rxjava3_android.local.entity.StudentEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class StudentRepositoryImpl(
    private val studentDao: StudentDao
) : StudentRepository {
    override fun insert(studentEntity: StudentEntity): Completable {
        return studentDao.insert(studentEntity)
    }

    override fun update(studentEntity: StudentEntity): Completable {
        return studentDao.update(studentEntity)
    }

    override fun delete(studentEntity: StudentEntity): Completable {
        return studentDao.delete(studentEntity)
    }

    override fun getAllStudent(): Flowable<List<StudentEntity>> {
        return studentDao.getAllStudent()
    }

    companion object {
        @Volatile
        private var INSTANCE: StudentRepository? = null

        fun getInstance(studentDao: StudentDao): StudentRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: StudentRepositoryImpl(studentDao)
            }.also { INSTANCE = it }
        }
    }
}
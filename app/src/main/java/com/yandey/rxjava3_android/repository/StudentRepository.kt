package com.yandey.rxjava3_android.repository

import com.yandey.rxjava3_android.local.entity.StudentEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface StudentRepository {
    fun insert(studentEntity: StudentEntity): Completable
    fun update(studentEntity: StudentEntity): Completable
    fun delete(studentEntity: StudentEntity): Completable
    fun getAllStudent(): Flowable<List<StudentEntity>>
}
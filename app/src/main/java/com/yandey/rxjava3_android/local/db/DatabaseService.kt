package com.yandey.rxjava3_android.local.db

import androidx.room.Database
import com.yandey.rxjava3_android.local.dao.StudentDao
import com.yandey.rxjava3_android.local.entity.StudentEntity

@Database(entities = [StudentEntity::class], version = 1, exportSchema = false)
abstract class DatabaseService {
    abstract fun studentDao(): StudentDao
}
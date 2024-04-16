package com.yandey.rxjava3_android.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yandey.rxjava3_android.local.dao.StudentDao
import com.yandey.rxjava3_android.local.entity.StudentEntity

@Database(entities = [StudentEntity::class], version = 1, exportSchema = false)
abstract class DatabaseService : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseService? = null

        fun getInstance(context: Context): DatabaseService {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): DatabaseService {
            return Room.databaseBuilder(
                context.applicationContext,
                DatabaseService::class.java,
                "student_database.db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
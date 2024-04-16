package com.yandey.rxjava3_android.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yandey.rxjava3_android.local.entity.StudentEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(studentEntity: StudentEntity): Completable

    @Update
    fun update(studentEntity: StudentEntity): Completable

    @Delete
    fun delete(studentEntity: StudentEntity): Completable

    @Query("SELECT * FROM student ORDER BY name ASC")
    fun getAllStudent(): Flowable<List<StudentEntity>>
}
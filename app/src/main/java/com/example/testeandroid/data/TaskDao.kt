package com.example.testeandroid.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAll(): LiveData<List<Task>>

    @Insert
    suspend fun insert(task: Task): Long

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}

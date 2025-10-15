package com.example.testeandroid.repo

import android.content.Context
import com.example.testeandroid.data.AppDatabase
import com.example.testeandroid.data.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val db: AppDatabase) {

    fun getTasks() = db.taskDao().getAll()

    suspend fun addTask(description: String): Long = withContext(Dispatchers.IO) {
        db.taskDao().insert(Task(description = description))
    }

    suspend fun updateTask(task: Task) = withContext(Dispatchers.IO) {
        db.taskDao().update(task)
    }

    suspend fun deleteTask(task: Task) = withContext(Dispatchers.IO) {
        db.taskDao().delete(task)
    }

    suspend fun verifyUser(username: String, passwordHash: String): Boolean = withContext(Dispatchers.IO) {
        val stored = db.userDao().getPasswordHash(username)
        stored != null && stored == passwordHash
    }
}

// convenience factory
fun createRepository(context: Context) = Repository(AppDatabase.getInstance(context))

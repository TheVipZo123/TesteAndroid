package com.example.testeandroid.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.testeandroid.data.Task
import com.example.testeandroid.repo.createRepository
import kotlinx.coroutines.launch

class TaskViewModel(app: Application): AndroidViewModel(app) {
    private val repo = createRepository(app.applicationContext)
    val tasks: LiveData<List<Task>> = repo.getTasks()

    fun addTask(desc: String) = viewModelScope.launch {
        if (desc.isBlank()) return@launch
        repo.addTask(desc.trim())
    }

    fun toggleDone(task: Task) = viewModelScope.launch {
        task.done = !task.done
        repo.updateTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        repo.deleteTask(task)
    }
}

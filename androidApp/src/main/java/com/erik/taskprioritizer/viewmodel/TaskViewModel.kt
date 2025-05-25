package com.erik.taskprioritizer.viewmodel

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.erik.taskprioritizer.model.Task
import com.erik.taskprioritizer.model.Weights
import com.erik.taskprioritizer.repository.TaskRepository
import com.erik.taskprioritizer.repository.WeightsRepository

class TaskViewModel: ViewModel() {
    private val taskRepo = TaskRepository()

    fun addTask(task: Task) = taskRepo.add(task)

    fun updateTask(task: Task) = taskRepo.update(task)

    fun getTasks(): List<Task> = taskRepo.getAll()

    fun getTaskById(id: String): Task? = taskRepo.findById(id)

    fun removeTask(id: String) = taskRepo.removeById(id)

    fun clearTasks() = taskRepo.clear()

    val expandedStates = mutableStateMapOf<String, Boolean>()

    fun toggleExpanded(taskId: String) {
        expandedStates[taskId] = !(expandedStates[taskId] ?: false)
    }

    fun isExpanded(taskId: String): Boolean {
        return expandedStates[taskId] ?: false
    }
}
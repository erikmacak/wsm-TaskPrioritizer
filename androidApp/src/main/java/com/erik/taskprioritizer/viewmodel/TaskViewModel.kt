package com.erik.taskprioritizer.viewmodel

import android.util.Log

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel

import com.erik.taskprioritizer.logic.TaskScorer
import com.erik.taskprioritizer.model.Task
import com.erik.taskprioritizer.repository.TaskRepository

class TaskViewModel: ViewModel() {
    private val taskRepo = TaskRepository

    fun addTask(task: Task) {
        taskRepo.add(task)
        Log.d("TaskViewModel", "Task added: ${task.getTitle()} [ID: ${task.getId()}]")
    }

    fun updateTask(task: Task) {
        taskRepo.update(task)
        Log.d("TaskViewModel", "Task updated: ${task.getTitle()} [ID: ${task.getId()}]")
    }

    fun updateTasks(tasks: List<Task>) {
        if (tasks.isEmpty()) {
            Log.d("TaskViewModel", "No tasks to update")
            return
        }

        taskRepo.updateAll(tasks)
        Log.d("TaskViewModel", "Updated ${tasks.size} tasks with new priority scores.")
    }

    fun getTasks(): List<Task> {
        Log.d("TaskViewModel", "Providing all tasks (${taskRepo.getAll().size})")
        return taskRepo.getAll()
    }

    fun getRankedTasks(): List<Task> {
        Log.d("TaskViewModel", "Providing all ranked tasks (${taskRepo.getAllRanked().size})")
        return taskRepo.getAllRanked()
    }

    fun getTasksByName(name: String): List<Task> {
        val result = taskRepo.getAllByName(name)
        Log.d("TaskViewModel", "Tasks filtered by name \"$name\": found ${result.size}")
        return result
    }

    fun getRankedTasksByName(name: String): List<Task>  {
        val result = taskRepo.getAllRankedByName(name)
        Log.d("TaskViewModel", "Ranked tasks filtered by name \"$name\": found ${result.size}")
        return result
    }

    fun getTaskById(id: String): Task {
        val task = taskRepo.findById(id)
        Log.d("TaskViewModel", "Task retrieved by ID: $id -> ${task.getTitle()}")
        return task
    }

    fun removeTask(id: String) {
        Log.d("TaskViewModel", "Removing task with ID: $id")
        taskRepo.removeById(id)
    }

    fun calculatePriorityScore(task: Task, weights: Map<String, Float>): Float {
        val score = TaskScorer.calculate(task, weights)
        Log.d("TaskViewModel", "Calculated priority score for '${task.getTitle()}' [ID: ${task.getId()}]: $score")
        return score
    }

    fun recalculateAllPriorityScore(tasks: List<Task>, weights: Map<String, Float>): List<Task> {
        Log.d("TaskViewModel", "Recalculated priority score for every stored task")
        return TaskScorer.recalculateAllTasks(tasks, weights)
    }

    private val expandedStates = mutableStateMapOf<String, Boolean>()

    fun toggleExpanded(taskId: String) {
        val newState = !(expandedStates[taskId] ?: false)
        expandedStates[taskId] = newState
        Log.d("TaskViewModel", "Toggled expanded state for task $taskId -> $newState")
    }

    fun isExpanded(taskId: String): Boolean {
        val state = expandedStates[taskId] ?: false
        Log.d("TaskViewModel", "Checked expanded state for task $taskId -> $state")
        return state
    }
}
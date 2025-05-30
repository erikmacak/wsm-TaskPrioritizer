package com.erik.taskprioritizer.util

import com.erik.taskprioritizer.model.Task

object ValidationUtils {
    fun isTaskTitleEmpty(taskTitle: String): Boolean {
        return taskTitle.isBlank()
    }

    fun isTaskTitleAlreadyRegistered(taskTitle: String, taskRepository: List<Task>): Boolean {
        return taskRepository.any { it.getTitle().equals(taskTitle, ignoreCase = true) }
    }

    fun isTaskTitleAlreadyRegisteredExceptCurrent(taskName: String, allTasks: List<Task>, currentTaskId: String): Boolean {
        return allTasks.any { task ->
            task.getTitle().equals(taskName, ignoreCase = true) && task.getId() != currentTaskId
        }
    }

    fun isWeightSumEqualToOne(weights: Map<String, Float>): Boolean {
        return weights.values.sum() in 0.99f..1.01f
    }

    fun isAtLeastOneTaskInTaskRepository(taskRepository: List<Task>): Boolean {
        return taskRepository.isNotEmpty()
    }
}
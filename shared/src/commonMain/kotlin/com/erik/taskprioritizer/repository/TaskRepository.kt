package com.erik.taskprioritizer.repository

import com.erik.taskprioritizer.model.Task

object TaskRepository {
    private val taskList = mutableListOf<Task>()

    fun add(task: Task) {
        taskList.add(task)
    }

    fun getAll(): List<Task> {
        return taskList.toList()
    }

    fun findById(id: String): Task? {
        return taskList.find { it.id == id }
    }

    fun removeById(id: String) {
        val iterator = taskList.iterator()
        while (iterator.hasNext()) {
            if (iterator.next().id == id) {
                iterator.remove()
            }
        }
    }

    fun clear() {
        taskList.clear()
    }
}
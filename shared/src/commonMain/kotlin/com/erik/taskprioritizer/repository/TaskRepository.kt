package com.erik.taskprioritizer.repository

import com.erik.taskprioritizer.model.Task

class TaskRepository {
    private val taskList = mutableListOf<Task>()

    fun add(task: Task) {
        taskList.add(task)
    }

    fun update(taskToUpdate: Task) {
        val index = taskList.indexOfFirst { it.getId() == taskToUpdate.getId() }
        if (index != -1) {
            taskList[index] = taskToUpdate
        }
    }

    fun getAll(): List<Task> {
        return taskList.toList()
    }

    fun findById(id: String): Task? {
        return taskList.find {it.getId() == id}
    }

    fun removeById(id: String) {
        val iterator = taskList.iterator()
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove()
            }
        }
    }

    fun clear() {
        taskList.clear()
    }
}
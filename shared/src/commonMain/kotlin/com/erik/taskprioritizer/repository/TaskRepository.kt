package com.erik.taskprioritizer.repository

import com.erik.taskprioritizer.logic.TaskRanker

import com.erik.taskprioritizer.model.Task

object TaskRepository {
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

    fun updateAll(tasksToUpdate: List<Task>) {
        tasksToUpdate.forEach { task ->  update(task) }
    }

    fun getAll(): List<Task> {
        return taskList.toList()
    }

    fun getAllRanked(): List<Task> {
        return TaskRanker.rankAll(taskList)
    }

    fun getAllByName(name: String): List<Task> {
        return taskList.filter { task ->
            task.getTitle().contains(name, ignoreCase = true)
        }
    }

    fun getAllRankedByName(name: String): List<Task> {
        return getAllRanked().filter { task ->
            task.getTitle().contains(name, ignoreCase = true)
        }
    }

    fun findById(id: String): Task {
        return taskList.find {it.getId() == id}!!
    }

    fun removeById(id: String) {
        val iterator = taskList.iterator()
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove()
            }
        }
    }
}
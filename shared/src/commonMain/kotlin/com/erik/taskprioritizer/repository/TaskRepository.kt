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

    fun getAllRanked(): List<Task> {
        val sortedTasks = taskList.sortedByDescending { it.getPriorityScore() }

        var currentRank = 1
        var previousScore: Float? = null
        var sameRankCount = 0

        return sortedTasks.mapIndexed { index, task ->
            val rank = if (task.getPriorityScore() == previousScore) {
                currentRank
            } else {
                currentRank + sameRankCount
            }

            sameRankCount = if (task.getPriorityScore() == previousScore) sameRankCount + 1 else 1
            previousScore = task.getPriorityScore()
            currentRank = rank

            task.copy(rank = rank)
        }
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
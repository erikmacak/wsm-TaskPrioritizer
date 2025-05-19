package repository

import model.Task

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
        taskList.removeIf { it.id == id }
    }

    fun clear() {
        taskList.clear()
    }
}
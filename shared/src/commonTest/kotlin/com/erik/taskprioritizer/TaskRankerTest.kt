package com.erik.taskprioritizer

import com.erik.taskprioritizer.logic.TaskRanker
import com.erik.taskprioritizer.model.Task
import kotlin.test.Test
import kotlin.test.assertEquals

class TaskRankerTest {

    @Test
    fun rankAllTasks() {
        val tasks = listOf(
            Task(title = "A", priorityScore = 4.1f),
            Task(title = "B", priorityScore = 4.7f),
            Task(title = "C", priorityScore = 7.4f)
        )

        val rankedTasks = TaskRanker.rankAll(tasks)
        assertEquals(listOf("C", "B", "A"), rankedTasks.map { it.getTitle() })
    }
}
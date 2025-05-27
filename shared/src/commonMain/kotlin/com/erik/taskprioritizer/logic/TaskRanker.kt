package com.erik.taskprioritizer.logic

import com.erik.taskprioritizer.model.Task

object TaskRanker {
    fun rankAll(tasks: List<Task>): List<Task> {
        val sortedTasks = tasks.sortedByDescending { it.getPriorityScore() }

        var currentRank = 1
        var previousScore: Float? = null
        var sameRankCount = 0

        return sortedTasks.map { task ->
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
}
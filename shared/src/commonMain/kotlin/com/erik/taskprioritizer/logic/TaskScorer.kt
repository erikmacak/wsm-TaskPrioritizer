package com.erik.taskprioritizer.logic

import com.erik.taskprioritizer.model.Task

object TaskScorer {
    fun calculate(task: Task, weights: Map<String, Float>): Float {
        return (
            task.getBenefit() * (weights["Benefit"]!!) +
                    task.getUrgency() * (weights["Urgency"]!!) +
                    (10 - task.getComplexity()) * (weights["Complexity"]!!) +
                    (10 - task.getRisk()) * (weights["Risk"]!!)
        )
    }

    fun recalculateAllTasks(tasks: List<Task>, weights: Map<String, Float>): List<Task> {
        return tasks.map { task ->
            task.copy(priorityScore = calculate(task, weights))
        }
    }
}
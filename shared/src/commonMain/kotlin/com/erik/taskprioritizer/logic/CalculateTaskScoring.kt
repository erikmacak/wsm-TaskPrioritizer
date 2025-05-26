package com.erik.taskprioritizer.logic

import com.erik.taskprioritizer.model.Task
import com.erik.taskprioritizer.model.Weights

fun calculateTaskScoring(task: Task, weights: Map<String, Float>): Float {
    return (task.getBenefit() * (weights["Benefit"] ?: 0f)) -
            (task.getComplexity() * (weights["Complexity"] ?: 0f)) +
            (task.getUrgency() * (weights["Urgency"] ?: 0f)) -
            (task.getRisk() * (weights["Risk"] ?: 0f))
}
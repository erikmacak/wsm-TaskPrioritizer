package com.erik.taskprioritizer.logic

import com.erik.taskprioritizer.model.Task
import com.erik.taskprioritizer.model.Weights

fun CalculateTaskScoring(task: Task, weights: Weights): Float {
    return (task.benefit * weights.benefit) - (task.complexity * weights.complexity) +
            (task.urgency * weights.urgency) - (task.risk * weights.risk)
}
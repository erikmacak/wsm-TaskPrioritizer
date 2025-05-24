package com.erik.taskprioritizer.logic

import com.erik.taskprioritizer.model.Task
import com.erik.taskprioritizer.model.Weights

fun calculateTaskScoring(task: Task, weights: Weights): Float {
    return (task.getBenefit() * weights.getBenefit()) - (task.getComplexity() * weights.getComplexity()) +
            (task.getUrgency() * weights.getUrgency()) - (task.getRisk() * weights.getRisk())
}
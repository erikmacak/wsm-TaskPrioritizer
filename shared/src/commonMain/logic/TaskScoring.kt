package logic

import model.Task
import model.Weights

object TaskScoring {
    fun calculate(task: Task, weights: Weights): Float {
        return (task.benefit * weights.benefit) - (task.complexity * weights.complexity) +
                (task.urgency * weights.urgency) - (task.risk * weights.risk)
    }
}
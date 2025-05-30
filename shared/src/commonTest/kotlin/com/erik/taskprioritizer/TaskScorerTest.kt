package com.erik.taskprioritizer

import com.erik.taskprioritizer.logic.TaskScorer
import com.erik.taskprioritizer.model.Task
import kotlin.test.Test
import kotlin.test.assertEquals

class TaskScorerTest {
    @Test
    fun calculatePriorityScore() {
        val task = Task(
            "abcdefgh-1234-7887-4321-abcdefgh1234",
            title = "do homework",
            benefit = 7,
            complexity = 5,
            urgency = 10,
            risk = 2
        )

        val weights = mapOf(
            "Benefit" to 0.2f,
            "Complexity" to 0.3f,
            "Urgency" to 0.2f,
            "Risk" to 0.3f
        )

        val expectedScore = 7.3f
        val score = TaskScorer.calculate(task, weights)

        assertEquals(expectedScore, score, 0.01f)
    }
}
package com.erik.taskprioritizer.model

import com.erik.taskprioritizer.util.generateUUID
import kotlinx.serialization.Serializable

@Serializable
data class Task (
    private val id: String = generateUUID(),
    private val title: String,
    private val benefit: Int = 0,
    private val complexity: Int = 0,
    private val urgency: Int = 0,
    private val risk: Int = 0,
    private var isExpanded: Boolean = false,
    private var priorityScore: Float = 0f,
    private var rank: Int = 0
) {
    fun getId(): String {
        return id
    }

    fun getTitle(): String {
        return this.title
    }

    fun getBenefit(): Int {
        return benefit
    }

    fun getComplexity(): Int {
        return complexity
    }

    fun getUrgency(): Int {
        return urgency
    }

    fun getRisk(): Int {
        return risk
    }

    fun getPriorityScore(): Float {
        return priorityScore
    }

    fun getRank(): Int {
        return rank
    }
}
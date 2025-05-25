package com.erik.taskprioritizer.model

import com.erik.taskprioritizer.util.generateUUID

data class Task (
    private val id: String = generateUUID(),
    private val name: String,
    private val benefit: Int,
    private val complexity: Int,
    private val urgency: Int,
    private val risk: Int,
    private var isExpanded: Boolean = false,
    private var priorityScore: Float = 0f,
    private var rank: Int = 0
) {
    fun getTitle(): String {
        return name
    }

    fun getId(): String {
        return id;
    }

    fun getBenefit(): Int {
        return benefit;
    }

    fun getComplexity(): Int {
        return complexity;
    }

    fun getUrgency(): Int {
        return urgency;
    }

    fun getRisk(): Int {
        return risk;
    }

    fun setPriorityScore(score: Float) {
        priorityScore = score;
    }

    fun getPriorityScore(): Float {
        return priorityScore;
    }

    fun getRank(): Int {
        return rank;
    }
}
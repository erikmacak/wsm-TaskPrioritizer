package com.erik.taskprioritizer.model

import com.erik.taskprioritizer.util.generateUUID

data class Task (
    val id: String = generateUUID(),
    val name: String,
    val benefit: Int,
    val complexity: Int,
    val urgency: Int,
    val risk: Int,
    var isExpanded: Boolean = false
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
}
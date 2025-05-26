package com.erik.taskprioritizer.repository

object WeightsRepository {
    private var weightsList: Map<String, Float> = mapOf(
        "Benefit" to 0.3f,
        "Complexity" to 0.3f,
        "Urgency" to 0.2f,
        "Risk" to 0.2f,
    )

    fun getAll(): Map<String, Float> = weightsList

    fun update(newWeightsValue: Map<String, Float>) {
        weightsList= newWeightsValue
    }
}
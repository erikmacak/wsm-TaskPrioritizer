package com.erik.taskprioritizer.repository

import com.erik.taskprioritizer.model.Weights

class WeightsRepository {
    private var weightsList: Map<String, Float> = mapOf(
        "Urgency" to 0.25f,
        "Risk" to 0.2f,
        "Complexity" to 0.3f,
        "Benefit" to 0.25f
    )

    fun getAll(): Map<String, Float> = weightsList

    fun update(newWeightsValue: Map<String, Float>) {
        weightsList= newWeightsValue
    }
}
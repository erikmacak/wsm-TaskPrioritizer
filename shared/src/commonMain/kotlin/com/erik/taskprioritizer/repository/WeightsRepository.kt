package com.erik.taskprioritizer.repository

import com.erik.taskprioritizer.model.Weights

class WeightsRepository {
    private var weightsList: Weights = Weights(urgency = 0.25f, risk = 0.2f, complexity = 0.3f, benefit = 0.25f)

    fun get(): Weights = weightsList

    fun update(newWeightsValue: Weights) {
        weightsList= newWeightsValue
    }
}
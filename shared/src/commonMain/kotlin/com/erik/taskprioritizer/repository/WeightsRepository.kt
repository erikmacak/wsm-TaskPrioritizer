package com.erik.taskprioritizer.repository

import com.erik.taskprioritizer.model.Weights

object WeightsRepository {
    private val weightsList = mutableListOf<Weights>()

    fun add(weights: Weights) {
        weightsList.add(weights)
    }

    fun getAll(): List<Weights> {
        return weightsList.toList()
    }

    fun clear() {
        weightsList.clear()
    }
}
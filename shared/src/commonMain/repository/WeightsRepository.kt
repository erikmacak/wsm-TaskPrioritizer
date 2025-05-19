package repository

import model.Weights

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
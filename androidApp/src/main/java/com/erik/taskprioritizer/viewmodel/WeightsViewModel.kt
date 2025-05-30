package com.erik.taskprioritizer.viewmodel

import android.util.Log

import androidx.lifecycle.ViewModel

import com.erik.taskprioritizer.repository.WeightsRepository

class WeightsViewModel: ViewModel() {
    private val weightsRepo = WeightsRepository

    fun getWeights(): Map<String, Float> {
        val weights = weightsRepo.getAll()
        Log.d("WeightsViewModel", "Retrieved weights: $weights")
        return weights
    }

    fun updateWeights(newWeights: Map<String, Float>) {
        weightsRepo.update(newWeights)
        Log.d("WeightsViewModel", "Updated weights: $newWeights")
    }
}
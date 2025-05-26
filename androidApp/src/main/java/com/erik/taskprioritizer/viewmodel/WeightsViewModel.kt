package com.erik.taskprioritizer.viewmodel

import androidx.lifecycle.ViewModel
import com.erik.taskprioritizer.model.Weights
import com.erik.taskprioritizer.repository.WeightsRepository

class WeightsViewModel: ViewModel() {
    private val weightsRepo = WeightsRepository

    fun getWeights(): Map<String, Float> = weightsRepo.getAll()

    fun updateWeights(newWeights: Map<String, Float>) = weightsRepo.update(newWeights)
}
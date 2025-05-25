package com.erik.taskprioritizer.viewmodel

import androidx.lifecycle.ViewModel
import com.erik.taskprioritizer.model.Weights
import com.erik.taskprioritizer.repository.WeightsRepository

class WeightsViewModel: ViewModel() {
    private val weightsRepo = WeightsRepository()

    fun getWeights(): Weights = weightsRepo.get()

    fun updateWeights(newWeights: Weights) = weightsRepo.update(newWeights)
}
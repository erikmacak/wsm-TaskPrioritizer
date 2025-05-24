package com.erik.taskprioritizer.model

data class Weights(
    private val benefit: Float,
    private val complexity: Float,
    private val urgency: Float,
    private val risk: Float
) {
    fun getBenefit(): Float {
        return benefit;
    }

    fun getComplexity(): Float {
        return complexity;
    }

    fun getUrgency(): Float {
        return urgency;
    }

    fun getRisk(): Float {
        return risk;
    }
}
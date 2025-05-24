package com.erik.taskprioritizer.viewmodel

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.toMutableStateMap
import androidx.lifecycle.ViewModel

class TaskViewModel: ViewModel() {
    val expandedStates = mutableStateMapOf<String, Boolean>()

    fun toggleExpanded(taskId: String) {
        expandedStates[taskId] = !(expandedStates[taskId] ?: false)
    }

    fun isExpanded(taskId: String): Boolean {
        return expandedStates[taskId] ?: false
    }
}
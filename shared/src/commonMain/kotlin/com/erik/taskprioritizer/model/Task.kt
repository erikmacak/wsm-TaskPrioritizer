package com.erik.taskprioritizer.model

import com.erik.taskprioritizer.util.generateUUID

data class Task (
    val id: String = generateUUID(),
    val name: String,
    val benefit: Int,
    val complexity: Int,
    val urgency: Int,
    val risk: Int,
    var isExpanded: Boolean = false
)
package model

import java.util.UUID;

data class Task (
    val id: String = UUID.randomUUID().ToString(),
    val name: String,
    val benefit: Int,
    val complexity: Int,
    val urgency: Int,
    val risk:
)
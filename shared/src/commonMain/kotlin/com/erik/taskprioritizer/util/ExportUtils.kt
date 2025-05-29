package com.erik.taskprioritizer.util

import com.erik.taskprioritizer.model.Task

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ExportUtils {

    fun exportToCsv(tasks: List<Task>): String {
        val header = "Name,Benefit,Complexity,Urgency,Risk,Priority\n"
        val body = tasks.joinToString("\n") {
            "${it.getTitle()},${it.getBenefit()},${it.getComplexity()},${it.getUrgency()},${it.getRisk()},${it.getPriorityScore()}"
        }
        return header + body
    }

    private val jsonFormatter = Json { prettyPrint = true }
    fun exportToJson(tasks: List<Task>): String {
        return jsonFormatter.encodeToString(tasks)
    }
}
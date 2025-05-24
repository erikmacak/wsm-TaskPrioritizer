package com.erik.taskprioritizer.util

import kotlin.random.Random

fun generateUUID(): String {
    val chars = "0123456789abcdef"
    val uuid = StringBuilder()
    val sections = listOf(8, 4, 4, 4, 12)
    for ((index, section) in sections.withIndex()) {
        repeat(section) {
            uuid.append(chars[Random.nextInt(chars.length)])
        }
        if (index < sections.size - 1) {
            uuid.append("-")
        }
    }
    return uuid.toString()
}
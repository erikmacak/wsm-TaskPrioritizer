package com.erik.taskprioritizer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package com.erik.taskprioritizer.ui

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AddTaskFormScreen () {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Text(
            "Add Task Form Screen should appear",
            color = Color.Black
        )
    }
}
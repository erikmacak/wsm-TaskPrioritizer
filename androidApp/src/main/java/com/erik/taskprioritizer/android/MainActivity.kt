package com.erik.taskprioritizer.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.erik.taskprioritizer.ui.theme.TaskPrioritizerTheme
import com.erik.taskprioritizer.ui.TaskListScreen
import com.erik.taskprioritizer.ui.AddTaskFormScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskPrioritizerTheme {
                //TaskListScreen()
                AddTaskFormScreen()
            }
        }
    }
}
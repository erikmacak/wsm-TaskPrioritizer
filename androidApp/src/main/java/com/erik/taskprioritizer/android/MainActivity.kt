package com.erik.taskprioritizer.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.erik.taskprioritizer.ui.theme.TaskPrioritizerTheme
import com.erik.taskprioritizer.ui.AdjustWeightsScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskPrioritizerTheme {
                //TaskListScreen()
                //AddTaskFormScreen()
                AdjustWeightsScreen()
            }
        }
    }
}
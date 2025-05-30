package com.erik.taskprioritizer.android

import android.os.Build
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi

import com.erik.taskprioritizer.navigation.AppNavigation
import com.erik.taskprioritizer.ui.theme.TaskPrioritizerTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskPrioritizerTheme {
                AppNavigation()
            }
        }
    }
}
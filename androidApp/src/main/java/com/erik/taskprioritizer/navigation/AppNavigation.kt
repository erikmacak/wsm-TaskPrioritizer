package com.erik.taskprioritizer.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.erik.taskprioritizer.ui.AddTaskFormScreen
import com.erik.taskprioritizer.ui.AdjustWeightsScreen
import com.erik.taskprioritizer.ui.TaskListScreen
import com.erik.taskprioritizer.ui.EditTaskFormScreen
import com.erik.taskprioritizer.ui.PriorityTasksListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.TaskList.route
    ) {
        composable(NavigationDestination.TaskList.route) {
            TaskListScreen (
                onEditClick = {
                    navController.navigate(NavigationDestination.EditTask.route)
                },
                onPrioritiesClick = {
                    navController.navigate(NavigationDestination.PriorityTaskList.route)
                },
                onAddTaskClick = {
                    navController.navigate(NavigationDestination.AddTask.route)
                },
                onAdjustWeightsClick = {
                    navController.navigate(NavigationDestination.AdjustWeights.route)
                }
            )
        }

        composable(NavigationDestination.EditTask.route) {
            EditTaskFormScreen(
                onBackClick = {
                    navController.navigate(NavigationDestination.TaskList.route)
                }
            )
        }

        composable(NavigationDestination.PriorityTaskList.route) {
            PriorityTasksListScreen(
                onAllClick = {
                    navController.navigate(NavigationDestination.TaskList.route)
                }
            )
        }

        composable(NavigationDestination.AddTask.route) {
            AddTaskFormScreen(
                onBackClick = {
                    navController.navigate(NavigationDestination.TaskList.route)
                }
            )
        }

        composable(NavigationDestination.AdjustWeights.route) {
            AdjustWeightsScreen(
                onBackClick = {
                    navController.navigate(NavigationDestination.TaskList.route)
                }
            )
        }
    }
}
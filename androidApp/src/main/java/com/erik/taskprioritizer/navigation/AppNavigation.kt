package com.erik.taskprioritizer.navigation

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.erik.taskprioritizer.ui.AddTaskFormScreen
import com.erik.taskprioritizer.ui.AdjustWeightsScreen
import com.erik.taskprioritizer.ui.TaskListScreen
import com.erik.taskprioritizer.ui.EditTaskFormScreen
import com.erik.taskprioritizer.ui.PriorityTasksListScreen

import com.erik.taskprioritizer.model.Task
import com.erik.taskprioritizer.repository.TaskRepository
import com.erik.taskprioritizer.viewmodel.TaskViewModel
import com.erik.taskprioritizer.viewmodel.WeightsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val taskViewModel: TaskViewModel = viewModel()
    val weightsViewModel: WeightsViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.TaskList.route
    ) {
        composable(NavigationDestination.TaskList.route) {
            TaskListScreen (
                taskViewModel = taskViewModel,
                weightsViewModel = weightsViewModel,
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
                },
                onSaveClick = { taskName, criteriaValues ->
                    val task = Task(
                        name = taskName,
                        benefit = (criteriaValues["Benefit"] ?: 0f).toInt(),
                        complexity = (criteriaValues["Complexity"] ?: 0f).toInt(),
                        urgency = (criteriaValues["Urgency"] ?: 0f).toInt(),
                        risk = (criteriaValues["Risk"] ?: 0f).toInt()
                    )
                    taskViewModel.addTask(task)
                    navController.popBackStack()
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
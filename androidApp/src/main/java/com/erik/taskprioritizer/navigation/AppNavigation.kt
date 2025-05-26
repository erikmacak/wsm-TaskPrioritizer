package com.erik.taskprioritizer.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
                onEditClick = { taskId ->
                    navController.navigate(NavigationDestination.EditTask.withId(taskId))
                },
                onRemoveClick = { taskId ->
                    navController.navigate(NavigationDestination.RemoveTask.withId(taskId))
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

        composable("edit_task/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")

            val task = taskViewModel.getTaskById(taskId.toString())
            EditTaskFormScreen(
                task = task,
                onBackClick = {
                    navController.navigate(NavigationDestination.TaskList.route)
                },
                onSaveClick = { taskName, criteriaValues ->
                    val updatedTask = task.copy(
                        title = taskName,
                        benefit = (criteriaValues["Benefit"] ?: 0f).toInt(),
                        complexity = (criteriaValues["Complexity"] ?: 0f).toInt(),
                        urgency = (criteriaValues["Urgency"] ?: 0f).toInt(),
                        risk = (criteriaValues["Risk"] ?: 0f).toInt()
                    )

                    val finalTask = updatedTask.copy(
                        priorityScore = taskViewModel.calculatePriorityScore(updatedTask, weightsViewModel.getWeights())
                    )

                    taskViewModel.updateTask(finalTask)

                    navController.navigate(NavigationDestination.TaskList.route)
                }
            )
        }

        composable("remove_task/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")
            
            LaunchedEffect(taskId) {
                taskViewModel.removeTask(taskId!!)
                navController.navigate(NavigationDestination.TaskList.route)

            }
        }

        composable(NavigationDestination.PriorityTaskList.route) {
            PriorityTasksListScreen(
                taskViewModel = taskViewModel,
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
                        title = taskName,
                        benefit = (criteriaValues["Benefit"] ?: 0f).toInt(),
                        complexity = (criteriaValues["Complexity"] ?: 0f).toInt(),
                        urgency = (criteriaValues["Urgency"] ?: 0f).toInt(),
                        risk = (criteriaValues["Risk"] ?: 0f).toInt()
                    )

                    val finalTask = task.copy(
                        priorityScore = taskViewModel.calculatePriorityScore(task, weightsViewModel.getWeights())
                    )

                    taskViewModel.addTask(finalTask)

                    navController.navigate(NavigationDestination.TaskList.route)
                }
            )
        }

        composable(NavigationDestination.AdjustWeights.route) {
            AdjustWeightsScreen(
                weightsViewModel = weightsViewModel,
                onBackClick = {
                    navController.navigate(NavigationDestination.TaskList.route)
                },
                onAdjustClick = { weightsValues ->
                    val newWeightsValues = listOf("Urgency", "Risk", "Complexity", "Benefit")
                        .associateWith { weightsValues[it] ?: 0f }

                    weightsViewModel.updateWeights(newWeightsValues)

                    taskViewModel.getTasks().forEach { task ->
                        val updatedTask = task.copy(
                            priorityScore = taskViewModel.calculatePriorityScore(task, weightsViewModel.getWeights())
                        )
                        taskViewModel.updateTask(updatedTask)
                    }

                    navController.navigate(NavigationDestination.TaskList.route)
                }
            )
        }
    }
}
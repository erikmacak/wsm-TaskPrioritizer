package com.erik.taskprioritizer.navigation

import android.util.Log
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
import com.erik.taskprioritizer.model.Weights
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
                onEditClick = { taskId ->
                    navController.navigate("${NavigationDestination.EditTask.route}/$taskId")
                },
                onRemoveClick = { taskId ->
                    navController.navigate("${NavigationDestination.RemoveTask.route}/$taskId")
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

        composable("${NavigationDestination.EditTask.route}/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")

            taskId?.let { id ->
                val task = taskViewModel.getTaskById(id)
                EditTaskFormScreen(
                    task = task,
                    onBackClick = {
                        navController.navigate(NavigationDestination.TaskList.route)
                    },
                    onSaveClick = { taskName, criteriaValues ->
                        if (task != null) {
                            val updatedTask = task.copy(
                                name = taskName,
                                benefit = (criteriaValues["Benefit"] ?: 0f).toInt(),
                                complexity = (criteriaValues["Complexity"] ?: 0f).toInt(),
                                urgency = (criteriaValues["Urgency"] ?: 0f).toInt(),
                                risk = (criteriaValues["Risk"] ?: 0f).toInt()
                            )
                            val priorityScore = taskViewModel.calculatePriorityScore(updatedTask, weightsViewModel.getWeights())
                            val finalTask = updatedTask.copy(priorityScore = priorityScore)
                            taskViewModel.updateTask(finalTask)
                        }
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("${NavigationDestination.RemoveTask.route}/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")
            
            LaunchedEffect(taskId) {
                if (taskId != null) {
                    taskViewModel.removeTask(taskId)
                    navController.popBackStack()
                }
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
                        name = taskName,
                        benefit = (criteriaValues["Benefit"] ?: 0f).toInt(),
                        complexity = (criteriaValues["Complexity"] ?: 0f).toInt(),
                        urgency = (criteriaValues["Urgency"] ?: 0f).toInt(),
                        risk = (criteriaValues["Risk"] ?: 0f).toInt()
                    )
                    val priorityScore = taskViewModel.calculatePriorityScore(task, weightsViewModel.getWeights())
                    val finalTask = task.copy(priorityScore = priorityScore)
                    taskViewModel.addTask(finalTask)
                    navController.popBackStack()
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
                    val urgency: Float = weightsValues["Urgency"] ?: 0f
                    val risk: Float = weightsValues["Risk"] ?: 0f
                    val complexity: Float = weightsValues["Complexity"] ?: 0f
                    val benefit: Float = weightsValues["Benefit"] ?: 0f

                    val newWeightsValues: Map<String, Float> = mapOf(
                        "Urgency" to urgency,
                        "Risk" to risk,
                        "Complexity" to complexity,
                        "Benefit" to benefit
                    )

                    weightsViewModel.updateWeights(newWeightsValues)

                    val updateTask = taskViewModel.getTasks().map { task ->
                        val updatedPriorityScore = taskViewModel.calculatePriorityScore(task, newWeightsValues)
                        task.setPriorityScore(updatedPriorityScore)
                    }

                    navController.popBackStack()
                }
            )
        }
    }
}
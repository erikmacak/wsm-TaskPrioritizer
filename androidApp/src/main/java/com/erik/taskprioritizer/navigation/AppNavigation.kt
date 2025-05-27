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
                onPrioritiesClick = {
                    Log.d("TaskListScreen", "Priorities section clicked")
                    navController.navigate(NavigationDestination.PriorityTaskList.route)
                },
                onEditClick = { taskId ->
                    Log.d("TaskListScreen", "Edit clicked for $taskId")
                    navController.navigate(NavigationDestination.EditTask.withId(taskId))
                },
                onRemoveClick = { taskId ->
                    Log.d("TaskListScreen", "Remove clicked for $taskId")
                    navController.navigate(NavigationDestination.RemoveTask.withId(taskId))
                },
                onAddTaskClick = {
                    Log.d("TaskListScreen", "Add task clicked")
                    navController.navigate(NavigationDestination.AddTask.route)
                },
                onAdjustWeightsClick = {
                    Log.d("TaskListScreen", "Adjust weights clicked")
                    navController.navigate(NavigationDestination.AdjustWeights.route)
                }
            )
        }

        composable(NavigationDestination.EditTask.routeWithArg) { backStackEntry ->
            val taskId = backStackEntry.arguments!!.getString("taskId")

            val task = taskViewModel.getTaskById(taskId.toString())
            EditTaskFormScreen(
                task = task,
                onBackClick = {
                    Log.d("EditTaskFormScreen", "Back button clicked")
                    navController.navigate(NavigationDestination.TaskList.route)
                },
                onSaveClick = { taskName, criteriaValues ->
                    Log.d("EditTaskFormScreen", "Save button clicked for $taskName")
                    val updatedTask = task.copy(
                        title = taskName,
                        benefit = (criteriaValues["Benefit"]!!).toInt(),
                        complexity = (criteriaValues["Complexity"]!!).toInt(),
                        urgency = (criteriaValues["Urgency"]!!).toInt(),
                        risk = (criteriaValues["Risk"]!!).toInt()
                    )

                    val finalTask = updatedTask.copy(
                        priorityScore = taskViewModel.calculatePriorityScore(updatedTask, weightsViewModel.getWeights())
                    )

                    taskViewModel.updateTask(finalTask)

                    navController.navigate(NavigationDestination.TaskList.route)
                }
            )
        }

        composable(NavigationDestination.RemoveTask.routeWithArg) { backStackEntry ->
            val taskId = backStackEntry.arguments!!.getString("taskId")
            
            LaunchedEffect(taskId) {
                taskViewModel.removeTask(taskId!!)
                navController.navigate(NavigationDestination.TaskList.route)
            }
        }

        composable(NavigationDestination.PriorityTaskList.route) {
            PriorityTasksListScreen(
                taskViewModel = taskViewModel,
                onAllClick = {
                    Log.d("PriorityTaskListScreen", "All section clicked")
                    navController.navigate(NavigationDestination.TaskList.route)
                }
            )
        }

        composable(NavigationDestination.AddTask.route) {
            AddTaskFormScreen(
                onBackClick = {
                    Log.d("AddTaskFormScreen", "Back button clicked")
                    navController.navigate(NavigationDestination.TaskList.route)
                },
                onAddClick = { taskName, criteriaValues ->
                    Log.d("AddTaskFormScreen", "Add button clicked for $taskName")
                    val task = Task(
                        title = taskName,
                        benefit = (criteriaValues["Benefit"]!!).toInt(),
                        complexity = (criteriaValues["Complexity"]!!).toInt(),
                        urgency = (criteriaValues["Urgency"]!!).toInt(),
                        risk = (criteriaValues["Risk"]!!).toInt()
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
                    Log.d("AdjustWeightsScreen", "Back button clicked")
                    navController.navigate(NavigationDestination.TaskList.route)
                },
                onAdjustClick = { weightsValues ->
                    Log.d("AdjustWeightsScreen", "Adjust button clicked")
                    val newWeightsValues = listOf("Urgency", "Risk", "Complexity", "Benefit")
                        .associateWith { weightsValues[it]!!}

                    weightsViewModel.updateWeights(newWeightsValues)

                    val updatedTasks = taskViewModel.recalculateAllPriorityScore(taskViewModel.getTasks(), weightsViewModel.getWeights())
                    updatedTasks.forEach {  task ->  taskViewModel.updateTask(task) }

                    navController.navigate(NavigationDestination.TaskList.route)
                }
            )
        }
    }
}
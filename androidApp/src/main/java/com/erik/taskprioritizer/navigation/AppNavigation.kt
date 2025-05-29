package com.erik.taskprioritizer.navigation

import android.os.Build
import android.util.Log

import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.erik.taskprioritizer.ui.AddTaskFormScreen
import com.erik.taskprioritizer.ui.AdjustWeightsScreen
import com.erik.taskprioritizer.ui.EditTaskFormScreen
import com.erik.taskprioritizer.ui.PriorityTasksListScreen
import com.erik.taskprioritizer.ui.TaskListScreen

import com.erik.taskprioritizer.model.Task
import com.erik.taskprioritizer.util.ExportUtils
import com.erik.taskprioritizer.util.ValidationUtils
import com.erik.taskprioritizer.util.saveToDownloads
import com.erik.taskprioritizer.viewmodel.TaskViewModel
import com.erik.taskprioritizer.viewmodel.WeightsViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val taskViewModel: TaskViewModel = viewModel()
    val weightsViewModel: WeightsViewModel = viewModel()

    val snackbarMessage = remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }

    val context = LocalContext.current

    //Navigation structure definition
    NavHost(
        navController = navController,
        startDestination = NavigationDestination.TaskList.route
    ) {
        // Main screen showing list of tasks
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

        // Screen for editing an existing task
        composable(NavigationDestination.EditTask.routeWithArg) { backStackEntry ->
            val taskId = backStackEntry.arguments!!.getString("taskId")

            val task = taskViewModel.getTaskById(taskId.toString())

            EditTaskFormScreen(
                task = task,
                snackbarMessage = snackbarMessage,
                snackbarHostState = snackbarHostState,
                onBackClick = {
                    Log.d("EditTaskFormScreen", "Back button clicked")
                    navController.navigate(NavigationDestination.TaskList.route)
                },
                onSaveClick = { taskName, criteriaValues ->
                    if (ValidationUtils.isTaskTitleEmpty(taskName)) {
                        Log.e("Validation", "Task title is empty")
                        snackbarMessage.value = "Task title cannot be empty"
                        return@EditTaskFormScreen
                    }

                    if (ValidationUtils.isTaskTitleAlreadyRegistered(taskName, taskViewModel.getTasks())) {
                        Log.e("Validation", "Task title already exists")
                        snackbarMessage.value = "Task with this name already exists"
                        return@EditTaskFormScreen
                    }

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

        // Route for task deletion – handled immediately via LaunchedEffect
        composable(NavigationDestination.RemoveTask.routeWithArg) { backStackEntry ->
            val taskId = backStackEntry.arguments!!.getString("taskId")
            
            LaunchedEffect(taskId) {
                taskViewModel.removeTask(taskId!!)
                navController.navigate(NavigationDestination.TaskList.route)
            }
        }

        // Screen showing prioritized tasks (sorted by priority score)
        // Includes export to CSV and JSON functionality
        composable(NavigationDestination.PriorityTaskList.route) {
            PriorityTasksListScreen(
                snackbarMessage = snackbarMessage,
                snackbarHostState = snackbarHostState,
                taskViewModel = taskViewModel,
                onAllClick = {
                    Log.d("PriorityTaskListScreen", "All section clicked")
                    navController.navigate(NavigationDestination.TaskList.route)
                },
                onCsvExportClick = {
                    if (ValidationUtils.isAtLeastOneTaskInTaskRepository(taskViewModel.getTasks())) {
                        Log.e("Validation", "Task repository is empty")
                        snackbarMessage.value = "There has to be at least one task to export"
                        return@PriorityTasksListScreen
                    }

                    val csv = ExportUtils.exportToCsv(taskViewModel.getRankedTasks())
                    saveToDownloads(context, "tasks.csv", "text/csv", csv)
                },
                onJsonExportClick = {
                    if (ValidationUtils.isAtLeastOneTaskInTaskRepository(taskViewModel.getTasks())) {
                        Log.e("Validation", "Task repository is empty")
                        snackbarMessage.value = "There has to be at least one task to export"
                        return@PriorityTasksListScreen
                    }

                    val json = ExportUtils.exportToJson(taskViewModel.getRankedTasks())
                    saveToDownloads(context, "tasks.json", "application/json", json)
                }
            )
        }

        // Screen for adding a new task to the list
        composable(NavigationDestination.AddTask.route) {
            AddTaskFormScreen(
                snackbarMessage = snackbarMessage,
                snackbarHostState = snackbarHostState,
                onBackClick = {
                    Log.d("AddTaskFormScreen", "Back button clicked")
                    navController.navigate(NavigationDestination.TaskList.route)
                },
                onAddClick = { taskName, criteriaValues ->
                    Log.d("AddTaskFormScreen", "Add button clicked for $taskName")

                    if (ValidationUtils.isTaskTitleEmpty(taskName)) {
                        Log.e("Validation", "Task title is empty")
                        snackbarMessage.value = "Task title cannot be empty"
                        return@AddTaskFormScreen
                    }

                    if (ValidationUtils.isTaskTitleAlreadyRegistered(taskName, taskViewModel.getTasks())) {
                        Log.e("Validation", "Task title already exists")
                        snackbarMessage.value = "Task with this name already exists"
                        return@AddTaskFormScreen
                    }

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

        // Screen for adjusting the weight values of criteria (Benefit, Complexity, etc.)
        composable(NavigationDestination.AdjustWeights.route) {
            AdjustWeightsScreen(
                weightsViewModel = weightsViewModel,
                snackbarMessage = snackbarMessage,
                snackbarHostState = snackbarHostState,
                onBackClick = {
                    Log.d("AdjustWeightsScreen", "Back button clicked")
                    navController.navigate(NavigationDestination.TaskList.route)
                },
                onAdjustClick = { weightsValues ->
                    if (!ValidationUtils.isWeightSumEqualToOne(weightsValues)) {
                        Log.e("Validation", "Weights sum does not equal one")
                        snackbarMessage.value = "Weights sum must be equal to one"
                        return@AdjustWeightsScreen
                    }

                    Log.d("AdjustWeightsScreen", "Adjust button clicked")
                    val newWeightsValues = listOf("Benefit", "Complexity", "Urgency", "Risk")
                        .associateWith { weightsValues[it]!!}

                    weightsViewModel.updateWeights(newWeightsValues)

                    val updatedTasks = taskViewModel.recalculateAllPriorityScore(taskViewModel.getTasks(), weightsViewModel.getWeights())
                    taskViewModel.updateTasks(updatedTasks)

                    navController.navigate(NavigationDestination.TaskList.route)
                }
            )
        }
    }
}
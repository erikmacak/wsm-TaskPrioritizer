package com.erik.taskprioritizer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.erik.taskprioritizer.ui.components.IconButtons
import com.erik.taskprioritizer.ui.components.IntroductoryText
import com.erik.taskprioritizer.ui.components.SearchBar
import com.erik.taskprioritizer.ui.components.SelectableTabButton
import com.erik.taskprioritizer.ui.components.TaskItemCard

import com.erik.taskprioritizer.model.Task
import com.erik.taskprioritizer.viewmodel.TaskViewModel
import com.erik.taskprioritizer.viewmodel.WeightsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun TaskListScreen(
    taskViewModel: TaskViewModel,
    weightsViewModel: WeightsViewModel,
    onEditClick: (taskId: String) -> Unit,
    onRemoveClick: (taskId: String) -> Unit,
    onPrioritiesClick: () -> Unit,
    onAddTaskClick: () -> Unit,
    onAdjustWeightsClick: () -> Unit) {
    // State variable to hold the current search query
    var searchQuery by remember { mutableStateOf("") }

    //
    val tasks = taskViewModel.getTasks()

    //
    val weights = weightsViewModel.getWeights()

    // Main column layout for the UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {

        // Introductory text at the top of the screen
        IntroductoryText(
            text = "Product Log - Tasks",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Search bar for filtering tasks
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Row for selecting between all tasks and priority tasks
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Button for selecting all tasks
            SelectableTabButton(
                text = "All",
                selected = true) { }

            // Button for selecting priority tasks
            SelectableTabButton(
                text = "Priorities",
                selected = false,
                onClick = onPrioritiesClick)
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn {
            items(tasks) { task ->
                TaskItemCard(
                    task = task,
                    isExpanded = taskViewModel.isExpanded(task.getId()),
                    onExpandClick = { taskViewModel.toggleExpanded(task.getId()) },
                    onEditClick =  { onEditClick(task.getId()) },
                    onRemoveClick = { onRemoveClick(task.getId()) },
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Row for icon buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
        ) {
            IconButtons(onAddTaskCLick = onAddTaskClick, onAdjustWeightsClick = onAdjustWeightsClick)
        }
    }
}
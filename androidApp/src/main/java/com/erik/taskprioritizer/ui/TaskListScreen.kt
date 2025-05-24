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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.erik.taskprioritizer.model.Weights


@Composable
fun TaskListScreen(taskViewModel: TaskViewModel = viewModel()) {
    // State variable to hold the current search query
    var searchQuery by remember { mutableStateOf("") }

    // Example task with dummy values
    var tasks by remember {
        mutableStateOf(
            listOf(
                Task(
                    id = "d52a4216-0acc-43d7-ba00-d3ffdeecc59b",
                    name = "Fix landing page",
                    benefit = 4,
                    complexity = 2,
                    urgency = 3,
                    risk = 1
                )
            )
        )
    }

    // Initialize weights for task scoring
    var weights = Weights(
        urgency = 0.25f,
        risk = 0.2f,
        complexity = 0.3f,
        benefit = 0.25f
    )

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
                onClick = { /* Navigace na PriorityTasksListScreen */ })
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn {
            items(tasks) { task ->
                TaskItemCard(
                    task = task,
                    isExpanded = taskViewModel.isExpanded(task.getId()),
                    onExpandClick = { taskViewModel.toggleExpanded(task.getId()) },
                    onEditClick = { /* ... */ },
                    onRemoveClick = { /* ... */ },
                    weights = weights
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
            IconButtons()
        }
    }
}
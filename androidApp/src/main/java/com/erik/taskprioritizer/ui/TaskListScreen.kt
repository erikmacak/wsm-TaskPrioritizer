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


@Composable
fun TaskListScreen() {
    // State variable to hold the current search query
    var searchQuery by remember { mutableStateOf("") }

    // List of tasks to be displayed
    val tasks = listOf(
        "Fix landing page",
        "Share prototype with team",
        "Add unit tests",
        "Add e2e tests",
        "Configure db connection"
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

        // LazyColumn to display the list of tasks
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(tasks) { task ->
                TaskItemCard(taskTitle = task)
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
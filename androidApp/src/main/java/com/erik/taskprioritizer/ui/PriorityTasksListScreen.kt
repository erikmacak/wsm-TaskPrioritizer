package com.erik.taskprioritizer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.erik.taskprioritizer.ui.components.ExportPossibilities
import com.erik.taskprioritizer.ui.components.IntroductoryText
import com.erik.taskprioritizer.ui.components.RankedTaskItemCard
import com.erik.taskprioritizer.ui.components.SearchBar
import com.erik.taskprioritizer.ui.components.SelectableTabButton
import com.erik.taskprioritizer.ui.theme.Montserrat
import com.erik.taskprioritizer.viewmodel.TaskViewModel

@Composable
fun PriorityTasksListScreen(
    snackbarMessage: MutableState<String?>,
    snackbarHostState: SnackbarHostState,
    taskViewModel: TaskViewModel,
    onAllClick: () -> Unit,
    onCsvExportClick: () -> Unit,
    onJsonExportClick: ()-> Unit) {

    // State variable to hold the current search query
    var searchQuery by remember { mutableStateOf("") }

    // Dynamically filters ranked tasks based on search input
    val rankedTasks by remember(searchQuery, taskViewModel) {
        derivedStateOf {
            if (searchQuery.isNotBlank()) {
                taskViewModel.getRankedTasksByName(searchQuery)
            } else {
                taskViewModel.getRankedTasks()
            }
        }
    }

    // Shows a snackbar message when one is set
    LaunchedEffect(snackbarMessage.value) {
        snackbarMessage.value?.let {
            snackbarHostState.showSnackbar(it)
            snackbarMessage.value = null
        }
    }

    // Main scaffold structure with Snackbar support
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {paddingValues ->
        // Main column layout for the UI
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            // Introductory text at the top of the screen
            IntroductoryText(
                text = "Sorted By Priorities",
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
                    selected = false,
                    onClick = onAllClick)

                // Button for selecting priority tasks
                SelectableTabButton(
                    text = "Priorities",
                    selected = true) { }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // LazyColumn to display the list of priority tasks
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(rankedTasks) { task ->
                    RankedTaskItemCard(taskTitle = task.getTitle(), taskRank = task.getRank())
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            // Row for export possibilities
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Export to:",
                    color = Color.White,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                ExportPossibilities(onCsvExportClick, onJsonExportClick)
            }
        }
    }
}
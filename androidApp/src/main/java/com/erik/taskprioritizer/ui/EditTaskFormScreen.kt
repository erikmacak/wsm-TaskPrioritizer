package com.erik.taskprioritizer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.erik.taskprioritizer.model.Task
import com.erik.taskprioritizer.ui.components.BackActionButton
import com.erik.taskprioritizer.ui.components.CustomSlider
import com.erik.taskprioritizer.ui.components.IntroductoryText
import com.erik.taskprioritizer.ui.components.SearchBar
import com.erik.taskprioritizer.ui.components.SliderHeading
import com.erik.taskprioritizer.ui.theme.Green
import com.erik.taskprioritizer.ui.theme.Montserrat
import com.erik.taskprioritizer.ui.theme.TextGray

@ExperimentalMaterial3Api
@Composable
fun EditTaskFormScreen(
    task: Task,
    snackbarMessage: MutableState<String?>,
    snackbarHostState: SnackbarHostState,
    onBackClick: () -> Unit,
    onSaveClick: (taskName: String, Map<String, Float>) -> Unit) {

    // State variable to hold the current search query
    var searchQuery by remember { mutableStateOf("") }

    // List of criteria
    val criteria = listOf(
        "Benefit",
        "Complexity",
        "Urgency",
        "Risk"
    )

    // Holds current values of sliders for each criterion
    val sliderValues = remember {
        mutableStateMapOf<String, Float>().apply {
            criteria.forEach{ put(it, 0f) }
        }
    }

    // Initialize the form with values from the selected task
    LaunchedEffect(task) {
        searchQuery = task.getTitle()
        sliderValues["Benefit"] = task.getBenefit().toFloat()
        sliderValues["Complexity"] = task.getComplexity().toFloat()
        sliderValues["Urgency"] = task.getUrgency().toFloat()
        sliderValues["Risk"] = task.getRisk().toFloat()
    }

    // Show a snackbar message if one is provided
    LaunchedEffect(snackbarMessage.value) {
        snackbarMessage.value?.let {
            snackbarHostState.showSnackbar(it)
            snackbarMessage.value = null
        }
    }

    // Main layout with snackbar support
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
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
                text = "Edit Task",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Label for the task name input
            Text(
                text = "Task Name:",
                fontSize = 20.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            // Box for the search bar to input the task name
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 8.dp)
            ) {
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    textColor = TextGray,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Create sliders for each criterion
            criteria.forEach { label ->
                // Display the heading for the slider
                SliderHeading(label = label)

                CustomSlider(
                    value = sliderValues[label] ?: 0f,
                    onValueChange = { newValue -> sliderValues[label] = newValue }
                )

                Spacer(modifier = Modifier.height(20.dp))
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action buttons for editing or going back
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BackActionButton(onBackClick = onBackClick)

                TextButton(onClick = { onSaveClick(searchQuery, sliderValues.toMap()) } ) {
                    Text(
                        text = "SAVE",
                        color = Green,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}
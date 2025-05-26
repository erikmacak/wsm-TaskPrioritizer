package com.erik.taskprioritizer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    task: Task?,
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

    // State map to hold slider values for each criterion, initialized to 0
    val sliderValues = remember {
        mutableStateMapOf<String, Float>().apply {
            criteria.forEach{ put(it, 0f) }
        }
    }

    LaunchedEffect(task) {
        task?.let {
            searchQuery = it.getTitle()
            sliderValues["Benefit"] = it.getBenefit().toFloat()
            sliderValues["Complexity"] = it.getComplexity().toFloat()
            sliderValues["Urgency"] = it.getUrgency().toFloat()
            sliderValues["Risk"] = it.getRisk().toFloat()
        }
    }


    // Main column layout for the UI
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
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
                    text = "EDIT",
                    color = Green,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}
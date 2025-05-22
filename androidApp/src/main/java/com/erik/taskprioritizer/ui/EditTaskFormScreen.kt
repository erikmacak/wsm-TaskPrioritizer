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
fun EditTaskFormScreen() {
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

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        IntroductoryText(
            text = "Edit Task",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(12.dp))

        //Text and textfield element for task name
        Text(
            text = "Task Name:",
            fontSize = 20.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

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
                placeholderValue = "Create PR"
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

        //Action buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BackActionButton()

            TextButton(onClick = { /* upravení úkolu */ }) {
                Text(
                    text = "ADJUST",
                    color = Green,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}
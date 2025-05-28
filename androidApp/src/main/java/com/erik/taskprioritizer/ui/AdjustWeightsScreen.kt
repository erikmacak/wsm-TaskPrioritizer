package com.erik.taskprioritizer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.erik.taskprioritizer.ui.components.BackActionButton
import com.erik.taskprioritizer.ui.components.CustomSlider
import com.erik.taskprioritizer.ui.components.IntroductoryText
import com.erik.taskprioritizer.ui.components.SliderHeading

import com.erik.taskprioritizer.ui.theme.Green
import com.erik.taskprioritizer.ui.theme.Montserrat
import com.erik.taskprioritizer.ui.theme.TextGray
import com.erik.taskprioritizer.viewmodel.WeightsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdjustWeightsScreen(
    weightsViewModel: WeightsViewModel,
    snackbarMessage: MutableState<String?>,
    snackbarHostState: SnackbarHostState,
    onBackClick: () -> Unit,
    onAdjustClick: (Map<String, Float>) -> Unit) {

    val criteria = weightsViewModel.getWeights()

    // State map to hold slider values for each criterion, initialized to 0
    val sliderValues = remember {
        mutableStateMapOf<String, Float>().apply {
            criteria.forEach { (key, value) -> put(key, value) }
        }
    }

    LaunchedEffect(criteria) {
        criteria.forEach { (key, value) -> sliderValues[key] = value }
    }

    LaunchedEffect(snackbarMessage.value) {
        snackbarMessage.value?.let {
            snackbarHostState.showSnackbar(it)
            snackbarMessage.value = null
        }
    }

    Scaffold (
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
                text = "Adjust Weights",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Warning text about weight adjustment
            Text(
                text = "Please ensure that the sum of all criterion " +
                        "weights equals 1 before saving. This is " +
                        "important to maintain accurate and consistent " +
                        "priority calculations.",
                fontSize = 12.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Bold,
                color = TextGray,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(36.dp))

            // Create sliders for each criterion
            criteria.forEach { (label, _) ->
                SliderHeading(label = label)

                CustomSlider(
                    value = sliderValues[label]!!,
                    onValueChange = { sliderValues[label] = it },
                    valueRange = 0f..1f,
                    labels = (0..10).map { (it / 10.0).toString() },
                    roundToInt = false,
                    axisHorizontalPadding = 18.dp
                )

                Spacer(modifier = Modifier.height(20.dp))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Action buttons for adjusting or going back
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BackActionButton(onBackClick = onBackClick)

                TextButton(onClick = { onAdjustClick(sliderValues.toMap()) }) {
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
}
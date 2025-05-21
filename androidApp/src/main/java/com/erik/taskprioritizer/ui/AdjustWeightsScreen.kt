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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erik.taskprioritizer.ui.components.CustomSlider
import com.erik.taskprioritizer.ui.theme.Blue
import com.erik.taskprioritizer.ui.theme.Green
import com.erik.taskprioritizer.ui.theme.Montserrat
import com.erik.taskprioritizer.ui.theme.TextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdjustWeightsScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Adjust Weights",
            fontSize = 24.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Black,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

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

        Text(
            text = "Benefit: ",
            fontSize = 20.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        var benefit by remember { mutableFloatStateOf(0f) }

        CustomSlider(
            value = benefit,
            onValueChange = { benefit = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Complexity: ",
            fontSize = 20.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        var complexity by remember { mutableFloatStateOf(0f) }

        CustomSlider(
            value = complexity,
            onValueChange = { complexity = it}
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Urgency: ",
            fontSize = 20.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        var urgency by remember { mutableFloatStateOf(0f) }

        CustomSlider(
            value = urgency,
            onValueChange = { urgency = it}
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Benefit: ",
            fontSize = 20.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        var risk by remember { mutableFloatStateOf(0f) }

        CustomSlider(
            value = risk,
            onValueChange = { risk = it}
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = { /* TODO: zpět akce */ }) {
                Text(
                    text = "BACK",
                    color = Blue,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            TextButton(onClick = { /* TODO: uložení akce */ }) {
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
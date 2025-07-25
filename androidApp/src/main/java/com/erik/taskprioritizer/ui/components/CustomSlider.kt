package com.erik.taskprioritizer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.erik.taskprioritizer.ui.theme.BackgroundGray
import com.erik.taskprioritizer.ui.theme.Blue
import com.erik.taskprioritizer.ui.theme.Montserrat

import kotlin.math.roundToInt

@ExperimentalMaterial3Api
@Composable
fun CustomSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..10f,
    steps: Int = 9,
    labels: List<String> = (0..10).map { it.toString() },
    roundToInt: Boolean = true,
    axisHorizontalPadding: Dp = 20.dp
) {
    Column(modifier = modifier) {
        Slider(
            value = value,
            onValueChange = {
                onValueChange(if (roundToInt) it.roundToInt().toFloat() else it)
            },
            valueRange = valueRange,
            steps = steps,
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Blue,
                inactiveTrackColor = BackgroundGray
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(40.dp),
            thumb = {
                Box(
                    modifier = Modifier
                        .size(width = 12.dp, height = 24.dp)
                        .background(Color.White, shape = RoundedCornerShape(50))
                )
            }
        )

        Row(
            modifier = Modifier.padding(horizontal = axisHorizontalPadding),
        ) {
            labels.forEach { label ->
                Text(
                    text = label,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
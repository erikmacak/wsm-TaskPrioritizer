package com.erik.taskprioritizer.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.erik.taskprioritizer.ui.theme.Montserrat

@Composable
fun IntroductoryText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 24.sp,
        fontFamily = Montserrat,
        fontWeight = FontWeight.Black,
        color = Color.White,
        modifier = modifier.padding(bottom = 16.dp)
    )
}
package com.erik.taskprioritizer.ui.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.erik.taskprioritizer.ui.theme.Blue
import com.erik.taskprioritizer.ui.theme.Montserrat

@Composable
fun BackActionButton() {
    TextButton(onClick = { /* zpět akce */ }) {
        Text(
            text = "BACK",
            color = Blue,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}
package com.erik.taskprioritizer.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import com.erik.taskprioritizer.ui.theme.Blue
import com.erik.taskprioritizer.ui.theme.Montserrat

@Composable
fun SelectableTabButton(text: String, selected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (selected) Blue else Color.Transparent

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(20.dp),
    ) {
        Text(
            text = text,
            color = Color.White,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Bold
        )
    }
}
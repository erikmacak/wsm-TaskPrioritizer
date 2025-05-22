package com.erik.taskprioritizer.ui.components

import com.erik.taskprioritizer.model.Task

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.erik.taskprioritizer.ui.theme.ContainerBackgroundColor
import com.erik.taskprioritizer.ui.theme.Green
import com.erik.taskprioritizer.ui.theme.Montserrat

@Composable
fun TaskItemCard(
    task: Task,
    onExpandClick: () -> Unit,
    onEditClick: () -> Unit,
    onRemoveClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "", //task.getTitle,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = onExpandClick) {
                Icon(
                    imageVector = if (task.isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        if (task.isExpanded) {
            Text(text = task.id, fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text("Benefit: ${task.benefit}", color = Color.White)
                    Text("Complexity: ${task.complexity}", color = Color.White)
                }
                Column {
                    Text("Urgency: ${task.urgency}", color = Color.White)
                    Text("Risk: ${task.risk}", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "PRIORITY SCORE: 0", //${calculateScore(task)}",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = onEditClick) {
                    Text("EDIT", color = Color(0xFFFFA500)) // Orange
                }
                TextButton(onClick = onRemoveClick) {
                    Text("REMOVE", color = Color.Red)
                }
            }
        }
    }
}
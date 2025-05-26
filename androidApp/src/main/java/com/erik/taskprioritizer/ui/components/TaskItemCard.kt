package com.erik.taskprioritizer.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import com.erik.taskprioritizer.ui.theme.ContainerBackgroundColor
import com.erik.taskprioritizer.ui.theme.Green
import com.erik.taskprioritizer.ui.theme.Montserrat

import com.erik.taskprioritizer.model.Task

import androidx.compose.foundation.clickable
import androidx.compose.material3.Divider
import androidx.compose.ui.unit.sp
import com.erik.taskprioritizer.ui.theme.BackgroundGray
import com.erik.taskprioritizer.ui.theme.Orange
import com.erik.taskprioritizer.ui.theme.Red
import com.erik.taskprioritizer.ui.theme.TextGray

@Composable
fun TaskItemCard(
    task: Task,
    isExpanded: Boolean,
    onExpandClick: () -> Unit,
    onEditClick: () -> Unit,
    onRemoveClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .animateContentSize(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Green),
        colors = CardDefaults.cardColors(
            containerColor = ContainerBackgroundColor
        )
    ) {
        Column (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = task.getTitle(),
                    color = Color.White,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                IconButton(onClick = onExpandClick) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowRight,
                        contentDescription = "Expand",
                        tint = Color.White
                    )
                }
            }

            Text(
                text = "PS: " + task.getPriorityScore(),
                color = TextGray,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )

            if (isExpanded) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = task.getId(),
                    color = TextGray,
                    fontSize = 15.sp
                )

                Row {
                    Text(
                        text = "Benefit: " + task.getBenefit().toString(),
                        color = Color.White,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.padding(horizontal = 34.dp))

                    Text(
                        text = "Urgency: " + task.getUrgency().toString(),
                        color = Color.White,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row {
                    Text(
                        text = "Complexity: " + task.getComplexity().toString(),
                        color = Color.White,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.padding(horizontal = 18.dp))

                    Text(
                        text = "Risk: " + task.getRisk().toString(),
                        color = Color.White,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold
                    )
                }

                Divider(
                    color = BackgroundGray,
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Edit",
                        color = Orange,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { onEditClick() }
                    )

                    Text(
                        text = "Remove",
                        color = Red,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { onRemoveClick() }
                    )
                }
            }
        }
    }
}
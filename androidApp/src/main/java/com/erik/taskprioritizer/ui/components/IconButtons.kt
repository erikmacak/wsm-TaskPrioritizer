package com.erik.taskprioritizer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.erik.taskprioritizer.android.R
import com.erik.taskprioritizer.ui.theme.Blue
import com.erik.taskprioritizer.ui.theme.Green
import com.erik.taskprioritizer.ui.theme.Orange

@Composable
fun IconButtons(
    onAddTaskCLick: () -> Unit,
    onAdjustWeightsClick: () -> Unit
) {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularIconButton(R.drawable.id_plus, "Add Task", Green, onAddTaskCLick)
            CircularIconButton(R.drawable.id_edit, "Edit Weights", Orange, onAdjustWeightsClick)
            //CircularIconButton(R.drawable.id_recalc, "Recalculate", Blue)
        }
    }
}

@Composable
fun CircularIconButton(iconId: Int, description: String, backgroundColor: Color, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(56.dp)
            .background(color = backgroundColor, shape = CircleShape)
            .padding(12.dp)
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = description,
            modifier = Modifier.fillMaxSize()
        )
    }
}
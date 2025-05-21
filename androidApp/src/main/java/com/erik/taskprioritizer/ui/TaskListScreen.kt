package com.erik.taskprioritizer.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import com.erik.taskprioritizer.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen() {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val tasks = listOf(
        "Fix landing page",
        "Share prototype with team",
        "Add unit tests",
        "Add e2e tests",
        "Configurate db connection"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Product Log - Tasks",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.DarkGray,
                    unfocusedContainerColor = Color.DarkGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                singleLine = true,
                shape = RoundedCornerShape(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("All", color = Color.White)
            }
            Text("Priorities", color = Color.White, modifier = Modifier.align(Alignment.CenterVertically))
        }


        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(tasks) { task ->
                TaskItem(taskTitle = task)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
        ) {
            CenteredIconButtons()
        }

    }
}

@Composable
fun TaskItem(taskTitle: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.Green),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = taskTitle,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Go to details",
                tint = Color.White
            )
        }
    }
}

@Composable
fun CenteredIconButtons() {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularIconButton(R.drawable.id_plus, "Add Task", Color.Green)
            CircularIconButton(R.drawable.id_edit, "Edit Weights", Color.Yellow)
            CircularIconButton(R.drawable.id_recalc, "Recalculate", Color.Blue)
        }
    }
}

@Composable
fun CircularIconButton(iconId: Int, description: String, backgroundColor: Color) {
    IconButton(
        onClick = { /* TODO */ },
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

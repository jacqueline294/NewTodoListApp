package com.example.todoapp.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditTaskPopup(
    currentTitle: String,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val containerColor = MaterialTheme.colorScheme.surface
    val contentColor = MaterialTheme.colorScheme.onSurface

    var editedTitle by remember {
        mutableStateOf(
            currentTitle
        )
    }

    Surface(
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 4.dp,
        color = containerColor,
        contentColor = contentColor
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Edit Task",
                style = MaterialTheme.typography.headlineSmall
            )
            TextField(
                value = editedTitle,
                onValueChange = { editedTitle = it },
                label = { Text("New Task Title") }
            )
            Row(
                modifier = Modifier.align(Alignment.End),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = onDismiss) {
                    Text("Cancel")
                }
                Button(onClick = { onConfirm(editedTitle) }) {
                    Text("Save")
                }
            }
        }
    }
}
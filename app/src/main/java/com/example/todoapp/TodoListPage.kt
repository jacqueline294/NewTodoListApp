package com.example.todoapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapp.Components.BottomNavigationBar
import com.example.todoapp.Components.TodoItem

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoListPage(navController: NavController, viewModel: TodoViewModel) {
    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Add a new task") },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (inputText.isNotBlank()) {
                        viewModel.addTodo(inputText)
                        inputText = ""  // Clears the input after adding the item
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Add Task")
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (todoList.isNullOrEmpty()) {
                Text(
                    "No items yet",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            } else {
                LazyColumn {
                    itemsIndexed(todoList!!) { index, item ->
                        TodoItem(item = item, onDelete = { viewModel.deleteTodo(item.id) }, onUpdate = { updatedTitle ->
                            val updatedTodo = item.copy(title = updatedTitle)
                            viewModel.updateTodo(updatedTodo)
                        })
                    }
                }
            }
        }
    }
}

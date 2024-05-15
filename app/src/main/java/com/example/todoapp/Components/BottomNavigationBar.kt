package com.example.todoapp.Components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.todoapp.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Todo List") },
            label = { Text("Todos") },
            selected = currentRoute == "todoList",
            onClick = {
                if (currentRoute != "todoList") {
                    navController.navigate("todoList")
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    ImageVector.vectorResource(id = R.drawable.baseline_wb_sunny_24),
                    contentDescription = "Weather"
                )
            },
            label = { Text("Weather") },
            selected = currentRoute == "weather",
            onClick = {
                if (currentRoute != "weather") {
                    navController.navigate("weather")
                }
            }
        )
    }
}

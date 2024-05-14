package com.example.todoapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.TodoListPage

import com.example.todoapp.weather.WeatherScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "todoList") {
        composable("todoList") {
            TodoListPage(navController, viewModel()) // Ensure ViewModel is passed if using DI or factory
        }
        composable("weather") {
            WeatherScreen(navController)
        }
    }
}

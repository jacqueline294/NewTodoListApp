package com.example.todoapp.navigation

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.TodoListPage
import com.example.todoapp.TodoViewModel
import com.example.todoapp.weather.WeatherScreen
import com.example.todoapp.weather.WeatherViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "todoList") {
        composable("todoList") {
            // Retrieve the TodoViewModel scoped to the NavGraph
            val todoViewModel: TodoViewModel = viewModel()
            // Pass the NavController and ViewModel to the TodoListPage
            TodoListPage(navController, todoViewModel)
        }
        composable("weatherScreen") {
            // Retrieve the WeatherViewModel scoped to the NavGraph
            val weatherViewModel: WeatherViewModel = viewModel()
            // Pass both ViewModel and NavController to the WeatherScreen
            WeatherScreen(weatherViewModel, navController)
        }
    }
}

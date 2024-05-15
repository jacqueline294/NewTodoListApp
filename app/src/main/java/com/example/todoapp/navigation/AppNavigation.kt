package com.example.todoapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.todoapp.TodoListPage
import com.example.todoapp.TodoViewModel // Ensure this import is correct
import com.example.todoapp.weather.WeatherScreen

object AppRoutes {
    const val TodoListPage = "mainScreen"
    const val WeatherScreen = "weatherScreen"
    const val WeatherDeepLink = "android-app://androidx.navigation/weather"
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppRoutes.TodoListPage) {
        composable(AppRoutes.TodoListPage) {

            val todoViewModel: TodoViewModel = viewModel()
            TodoListPage(navController, todoViewModel)
        }
        composable(
            AppRoutes.WeatherScreen,
            deepLinks = listOf(navDeepLink { uriPattern = AppRoutes.WeatherDeepLink })
        ) {
            WeatherScreen(navController)
        }
    }
}

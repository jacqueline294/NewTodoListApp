package com.example.todoapp.weather

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import java.util.Locale

@Composable
fun WeatherScreen(navController: NavController, viewModel: WeatherViewModel = viewModel()) {
    val weatherData = viewModel.weatherData.observeAsState()
    val errorMessage = viewModel.errorMessage.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            weatherData.value != null -> {
                WeatherDetails(weatherData = weatherData.value!!)
            }
            errorMessage.value != null -> {
                Column {
                    Text(
                        text = "Error: ${errorMessage.value}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Button(onClick = { viewModel.fetchWeatherData() }) {
                        Text("Retry")
                    }
                }
            }
            else -> {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun WeatherDetails(weatherData: WeatherData) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Weather in ${weatherData.name}", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Temperature: ${weatherData.main.temp}°C", style = MaterialTheme.typography.titleLarge)
        Text(text = "Conditions: ${weatherData.weather.first().description.capitalize(Locale.ROOT)}", style = MaterialTheme.typography.titleMedium)
    }
}

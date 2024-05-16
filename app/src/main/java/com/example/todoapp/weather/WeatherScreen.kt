package com.example.todoapp.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp.weather.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel, navController: NavController) {
    var cityName by remember { mutableStateOf("") }
    val weatherData = weatherViewModel.weatherData.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather Checker", color = Color.White) },
                Modifier.background(color= MaterialTheme.colorScheme.primary),
                actions = {
                    IconButton(onClick = { navController.navigate("todoList") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = cityName,
                onValueChange = { cityName = it },
                label = { Text("Enter City Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { weatherViewModel.loadWeather(cityName) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Get Weather", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (weatherData != null) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("City: ${weatherData.name}", style = MaterialTheme.typography.titleLarge)
                    Text("Temperature: ${weatherData.main.temp}°C", style = MaterialTheme.typography.bodyLarge)
                    Text("Condition: ${weatherData.weather.firstOrNull()?.description ?: "N/A"}", style = MaterialTheme.typography.bodyLarge)
                }
            } else if (cityName.isNotEmpty()) {
                Text("Loading or no data available...", style = MaterialTheme.typography.bodyLarge)
            } else {
                Text("Please enter a city name to check the weather.", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

package com.example.todoapp.weather

data class WeatherData(
    val main: Main,
    val weather: List<Weather>,
    val name: String
)

data class Main(
    val temp: Double
)

data class Weather(
    val description: String
)

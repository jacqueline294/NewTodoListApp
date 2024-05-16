package com.example.todoapp.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val weatherApi = RetrofitInstance.api
    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    fun loadWeather(city: String) {
        viewModelScope.launch {
            try {
                // Replace "your_api_key" with your actual API key
                val response = weatherApi.getWeather(city, "7d7bd87afcf07f5b227b8e501ddd7126")
                _weatherData.value = response
            } catch (e: Exception) {
                _weatherData.value = null  // Implement error handling, possibly updating the UI
            }
        }
    }
}

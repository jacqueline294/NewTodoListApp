package com.example.todoapp.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Response

class WeatherViewModel : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherService: WeatherService = retrofit.create(WeatherService::class.java)

    val weatherData = MutableLiveData<WeatherData>()
    val errorMessage = MutableLiveData<String>()

    fun loadWeather(city: String) {
        viewModelScope.launch {
            try {
                val response = weatherService.getCurrentWeather(city, "897a020f529640f7602410e9f97285ca")
                if (response.isSuccessful && response.body() != null) {
                    weatherData.postValue(response.body())
                } else {
                    errorMessage.postValue("Failed to fetch data: ${response.message()}")
                }
            } catch (e: Exception) {
                errorMessage.postValue("Error: ${e.message}")
            }
        }
    }
}

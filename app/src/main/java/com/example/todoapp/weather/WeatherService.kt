package com.example.todoapp.weather

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface WeatherService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = "897a020f529640f7602410e9f97285ca"
    ): Response<WeatherData>
}

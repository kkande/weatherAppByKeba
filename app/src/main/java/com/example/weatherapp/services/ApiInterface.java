package com.example.weatherapp.services;

import com.example.weatherapp.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("data/2.5/weather?")
    Call<WeatherResponse> getWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("appid") String app_id);

    @GET("data/2.5/weather?")
    Call<WeatherResponse> getCurrentWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);

    @GET("data/2.5/weather")
    Call<WeatherResponse> getCurrentWeatherByName(@Query("q") String name, @Query("APPID") String app_id);

}

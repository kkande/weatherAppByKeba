package com.example.weatherapp.services;

import android.database.Observable;

import com.example.weatherapp.model.WeatherResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("data/2.5/weather?")
    Call<WeatherResponse> getCurrentWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);

    @GET("data/2.5/weather?")
    Call<WeatherResponse> getCurrentWeatherByName(@Query("q") String name, @Query("APPID") String app_id);


    @GET("data/2.5/weather?")
    Observable<WeatherResponse> getWeatherByName(@Query("q") String name, @Query("APPID") String app_id);


}

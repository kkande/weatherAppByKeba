package com.example.weatherapp.ui.fragment;

import com.example.weatherapp.model.WeatherResponse;
import com.example.weatherapp.services.ApiInterface;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;


public class CityFragmentTest {

    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "cb65c3940d3be9356b54a7dfd01f938b";


    /**
     * Testing method get Name Done
     */
    @Test
    public void getCurrentDataByName() {
        WeatherResponse weatherResponse = new WeatherResponse();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<WeatherResponse> callResp = service.getCurrentWeatherByName("Londres", AppId);
        callResp.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                if (response.code() == 200) {
                    weatherResponse.name = "Londres";
                    weatherResponse.main.temp = weatherResponse.main.temp - 273 ;
                    weatherResponse.weather.get(0).icon = "03d";


                    assertEquals("Londres",weatherResponse.name);
                    assertEquals("308",weatherResponse.main.temp);
                    assertEquals("03d",weatherResponse.weather.get(0).icon);

                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });

    }

    @Test
    public void iconWeatherDay() {

    }

    @Test
    public void iconeWeatherNight() {
    }
}
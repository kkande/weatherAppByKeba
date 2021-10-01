package com.example.weatherapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.weatherapp.R;
import com.example.weatherapp.model.WeatherResponse;
import com.example.weatherapp.services.ApiInterface;
import com.example.weatherapp.utils.Retrofit2Instance;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;


import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class CityFragment extends Fragment {
    ImageView imageView ;
    private TextView textNom ,textTemp ;
    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "cb65c3940d3be9356b54a7dfd01f938b";
    public static String name = "Lyon";



    String nameCity,icone, temp;

    ApiInterface apiInterface;

    public CityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_city, container, false);

        textNom = (TextView)rootView.findViewById(R.id.text);
        textTemp = (TextView)rootView.findViewById(R.id.temperature);
        imageView = (ImageView)rootView.findViewById(R.id.icon);

        apiInterface = Retrofit2Instance.getRetrofit2Instance().create(ApiInterface.class);


        return rootView;
    }



    public void getCurrentDataByName(String city) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<WeatherResponse> callResp = service.getCurrentWeatherByName(city, AppId);
        callResp.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;

                    nameCity = getString(R.string.ville) + weatherResponse.name;

                    icone = weatherResponse.weather.get(0).icon ;

                    temp = getString(R.string.temp) +  String.format("%.0f", weatherResponse.main.temp - 273)+ "°C" ;


                    textNom.setText(nameCity);
                    textTemp.setText(temp);


                    iconWeatherDay();
                    iconeWeatherNight();

                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                textNom.setText(t.getMessage());
                textTemp.setText(t.getMessage());
            }
        });
    }


    public void iconWeatherDay(){

        switch (icone){
            case "01d" :
                imageView.setImageResource(R.drawable.clear_sky);
                break;


            case "02d" :
                imageView.setImageResource(R.drawable.fewclouds);
                break;


            case "03d" :
                imageView.setImageResource(R.drawable.scatteredclouds);


            case "04d" :
                imageView.setImageResource(R.drawable.brokenclouds);
                break;


            case "09d" :
                imageView.setImageResource(R.drawable.showerrain);
                break;


            case "10d" :
                imageView.setImageResource(R.drawable.rainy);
                break;


            case "11d" :
                imageView.setImageResource(R.drawable.thunderstorm);
                break;


            case "13d" :
                imageView.setImageResource(R.drawable.snow);
                break;


            case "50d" :
                imageView.setImageResource(R.drawable.mist);
                break;
        }

    }

    public void iconeWeatherNight(){
        switch (icone){
            case "01n" :
                imageView.setImageResource(R.drawable.clear_sky);
                break;


            case "02n" :
                imageView.setImageResource(R.drawable.fewclouds);
                break;


            case "03n" :
                imageView.setImageResource(R.drawable.scatteredclouds);


            case "04n" :
                imageView.setImageResource(R.drawable.brokenclouds);
                break;


            case "09n" :
                imageView.setImageResource(R.drawable.showerrain);
                break;


            case "10n" :
                imageView.setImageResource(R.drawable.rainy);
                break;


            case "11n" :
                imageView.setImageResource(R.drawable.thunderstorm);
                break;


            case "13n" :
                imageView.setImageResource(R.drawable.snow);
                break;


            case "50n" :
                imageView.setImageResource(R.drawable.mist);
                break;
        }
    }

   /* public void getData(String name){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface dataRequest = retrofit.create(ApiInterface.class);

        dataRequest.getWeatherByName(name, AppId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<WeatherResponse>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NotNull WeatherResponse weatherResponse) {
                        if (weatherResponse != null ){

                            nameCity = "Ville : " + weatherResponse.name;

                            icone = weatherResponse.weather.get(0).icon ;

                            temp = "Température: " + String.format("%.0f", weatherResponse.main.temp - 273)+ "°C" ;

                            textNom.setText(nameCity);
                            textTemp.setText(temp);

                        }

                    }

                    @Override
                    public void onError(@NotNull Throwable e) {

                    }
                });

    }
*/
/*
    public void getCurrentDataLongLat() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<WeatherResponse> call = service.getCurrentWeatherData(lat, lon, AppId);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;


                    String stringNom =
                            "Nom de ville : " +
                                    weatherResponse.name +
                                    "\n" +
                                    "Icon: " +
                                    weatherResponse.weather;

                    //  String iconCode = weatherResponse.weather


                    String stringTemp =
                            "Température: " +
                                    String.format("%.0f", weatherResponse.main.temp - 273)+ "°C" ;


                    textNom.setText(stringNom);
                    textTemp.setText(stringTemp);


                    // Picasso.get().load(icon_url).into(holder.ivIcon);

                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t)
            {
                textNom.setText(t.getMessage());
                textTemp.setText(t.getMessage());

            }
        });
    }
*/

}
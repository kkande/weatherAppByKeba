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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class CityFragment extends Fragment {
    private ImageView imageView;
    private TextView textNom ,textTemp ;
    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "cb65c3940d3be9356b54a7dfd01f938b";
    public static String lat = "45.764043";
    public static String lon = "4.5833";
    public static String name = "Lyon";

    private ApiInterface apiInterface;


    public CityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_city, container, false);


        textNom = (TextView)rootView.findViewById(R.id.text);
        textTemp = (TextView)rootView.findViewById(R.id.temperature);
        imageView = (ImageView)rootView.findViewById(R.id.icon);


        return rootView;
    }


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
                            weatherResponse.name ;


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

                    String stringNom =
                            "Nom de ville : " +
                                    weatherResponse.name ;


                    String stringTemp =
                            "Température: " +
                                    String.format("%.0f", weatherResponse.main.temp - 273)+ "°C" ;


                    textNom.setText(stringNom);
                    textTemp.setText(stringTemp);
                    // Picasso.get().load(icon_url).into(holder.ivIcon);


                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                textNom.setText(t.getMessage());
                textTemp.setText(t.getMessage());            }
        });
    }

}
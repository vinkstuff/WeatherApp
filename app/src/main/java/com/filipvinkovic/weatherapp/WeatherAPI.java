package com.filipvinkovic.weatherapp;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Filip on 11.12.2014..
 */
public class WeatherAPI {

    private static WeatherService weatherService;
    private static final String URL = "http://api.openweathermap.org/data/2.5";

    public interface WeatherService {

        @GET("/forecast/daily")
        void getWeatherInfo(@Query("lat") String latitude,
                            @Query("lon") String longitude,
                            @Query("units") String units,
                            @Query("cnt") String count,
                            Callback<WeatherData> cb);
    }

    public static WeatherService getApiService() {

        RestAdapter restAdapter = new RestAdapter.Builder().
                setEndpoint(URL).build();

        weatherService = restAdapter.create(WeatherService.class);

        return weatherService;
    }
}

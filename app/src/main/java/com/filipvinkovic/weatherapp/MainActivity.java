package com.filipvinkovic.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<WeatherData.DailyWeather> weatherDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherAPI.getApiService().getWeatherInfo("45.811572", "15.952148", "metric", "6",
                new Callback<WeatherData>() {
                    @Override
                    public void success(WeatherData weatherData, Response response) {
                        Date currentDate = new Date(Long.parseLong(weatherData.dailyWeatherList.get(0).unixDate)*1000);
                        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                        final int iconId = getResources().getIdentifier("w" + weatherData.dailyWeatherList.get(0).weather.get(0).icon, "drawable", getPackageName());
                        ((ImageView) findViewById(R.id.weather_icon_today)).setImageDrawable(getResources().getDrawable(iconId));
                        ((TextView) findViewById(R.id.weather_condition_today)).setText(weatherData.dailyWeatherList.get(0).weather.get(0).main);
                        ((TextView) findViewById(R.id.current_temp)).setText(String.valueOf(weatherData.dailyWeatherList.get(0).temp.day.intValue())+"°");
                        ((TextView) findViewById(R.id.min_max_temp)).setText("Min "+weatherData.dailyWeatherList.get(0).temp.min.intValue()+"° - Max "+weatherData.dailyWeatherList.get(0).temp.max.intValue()+"°");
                        ((TextView) findViewById(R.id.night_temp)).setText("Night "+weatherData.dailyWeatherList.get(0).temp.night.intValue() + "°");
                        ((TextView) findViewById(R.id.date)).setText(format.format(currentDate));

                        String weatherId = String.valueOf(weatherData.dailyWeatherList.get(0).weather.get(0).id);
                        int color = R.color.cloudy;
                        if(weatherId.startsWith("2")) {
                            color = getResources().getColor(R.color.thunderstorm);
                        } else if(weatherId.startsWith("3")) {
                            color = getResources().getColor(R.color.light_rain);
                        } else if(weatherId.startsWith("5")) {
                            color = getResources().getColor(R.color.rain);
                        } else if(weatherId.startsWith("6")) {
                            color = getResources().getColor(R.color.snow);
                        } else if(weatherId.startsWith("7")) {
                            color = getResources().getColor(R.color.fog);
                        } else if(weatherId.startsWith("8")) {
                            color = getResources().getColor(R.color.cloudy);
                        } else if(weatherId.startsWith("800")) {
                            color = getResources().getColor(R.color.sunny);
                        } else if(weatherId.startsWith("801")) {
                            color = getResources().getColor(R.color.partly_cloudy);
                        }

                        ((LinearLayout) findViewById(R.id.main_layout)).setBackgroundColor(color);

                        Log.d("WeatherApp", "DataSetChanged");
                        weatherDataList.addAll(weatherData.dailyWeatherList);
                        weatherDataList.remove(0);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("WeatherApp", error.getMessage());
                    }
                });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        weatherDataList = new ArrayList<WeatherData.DailyWeather>();
        adapter = new RecyclerViewAdapter(weatherDataList, this);
        recyclerView.setAdapter(adapter);
    }

}

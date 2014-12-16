package com.filipvinkovic.weatherapp.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.filipvinkovic.weatherapp.R;
import com.filipvinkovic.weatherapp.adapters.RecyclerViewAdapter;
import com.filipvinkovic.weatherapp.fragments.SplashFragment;
import com.filipvinkovic.weatherapp.fragments.WeatherLoaderFragment;
import com.filipvinkovic.weatherapp.rest.WeatherData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends Activity implements WeatherLoaderFragment.ProgressListener {

    private static final String TAG_WEATHER_LOADER = "weatherLoader";
    private static final String TAG_SPLASH_SCREEN = "splashScreen";

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<WeatherData.DailyWeather> weatherDataList;
    private WeatherLoaderFragment weatherLoaderFragment;
    private SplashFragment splashFragment;

    @Override
    public void onCompletion(WeatherData weatherData) {
        setContentView(R.layout.activity_main);

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

        weatherDataList = new ArrayList<>(weatherData.dailyWeatherList);
        weatherDataList.remove(0);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(weatherDataList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        weatherLoaderFragment = (WeatherLoaderFragment) fm.findFragmentByTag(TAG_WEATHER_LOADER);
        if(weatherLoaderFragment == null) {
            weatherLoaderFragment = new WeatherLoaderFragment();
            weatherLoaderFragment.setProgressListener(this);
            weatherLoaderFragment.startLoading();
            fm.beginTransaction().add(weatherLoaderFragment, TAG_WEATHER_LOADER).commit();
        }

        splashFragment = (SplashFragment) fm.findFragmentByTag(TAG_SPLASH_SCREEN);
        if(splashFragment == null) {
            splashFragment = new SplashFragment();
            fm.beginTransaction().add(android.R.id.content, splashFragment, TAG_SPLASH_SCREEN).commit();
        }
    }

}

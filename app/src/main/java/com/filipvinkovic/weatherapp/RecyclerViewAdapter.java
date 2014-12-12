package com.filipvinkovic.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Filip on 30.11.2014..
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<WeatherData> weatherDataList;

    public RecyclerViewAdapter(List<WeatherData> weatherDataList) {
        this.weatherDataList = weatherDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        /*WeatherData weatherData = weatherDataList.get(position);
        viewHolder.dayName.setText(weatherData.getDayName());
        viewHolder.weatherCondition.setText(weatherData.getWeatherCondition());
        viewHolder.minTemp.setText(weatherData.getMinTemp() + "°");
        viewHolder.maxTemp.setText(weatherData.getMaxTemp() + "°");

        switch (weatherData.getWeatherCondition()) {
            case "Cloudy":
                viewHolder.weatherIcon.setImageResource(R.drawable.s03d);
                break;
            case "Partly Cloudy":
                viewHolder.weatherIcon.setImageResource(R.drawable.s02d);
                break;
            case "Rain":
                viewHolder.weatherIcon.setImageResource(R.drawable.s09d);
                break;
            case "Fog":
                viewHolder.weatherIcon.setImageResource(R.drawable.s50d);
                break;
            case "Thunderstorm":
                viewHolder.weatherIcon.setImageResource(R.drawable.s11d);
                break;
            case "Snow":
                viewHolder.weatherIcon.setImageResource(R.drawable.s13d);
                break;
            case "Sunny":
                viewHolder.weatherIcon.setImageResource(R.drawable.s01d);
                break;
            case "Raind and sun":
                viewHolder.weatherIcon.setImageResource(R.drawable.s10d);
                break;
        }*/
    }

    @Override
    public int getItemCount() {
        return weatherDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView dayName,
                        weatherCondition,
                        minTemp,
                        maxTemp;
        private ImageView weatherIcon;

        public ViewHolder(View view) {
            super(view);
            dayName = (TextView) view.findViewById(R.id.day_name);
            weatherCondition = (TextView) view.findViewById(R.id.weather_condition);
            minTemp = (TextView) view.findViewById(R.id.min_temp);
            maxTemp = (TextView) view.findViewById(R.id.max_temp);
            weatherIcon = (ImageView) view.findViewById(R.id.weather_icon);
        }
    }
}

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

    private List<WeatherInfo> weatherInfoList;

    public RecyclerViewAdapter(List<WeatherInfo> weatherInfoList) {
        this.weatherInfoList = weatherInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        WeatherInfo weatherInfo = weatherInfoList.get(position);
        viewHolder.dayName.setText(weatherInfo.getDayName());
        viewHolder.weatherCondition.setText(weatherInfo.getWeatherCondition());
        viewHolder.minTemp.setText(weatherInfo.getMinTemp() + "°");
        viewHolder.maxTemp.setText(weatherInfo.getMaxTemp() + "°");
    }

    @Override
    public int getItemCount() {
        return weatherInfoList.size();
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

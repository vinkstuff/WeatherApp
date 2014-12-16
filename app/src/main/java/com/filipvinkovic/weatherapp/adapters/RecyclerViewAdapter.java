package com.filipvinkovic.weatherapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.filipvinkovic.weatherapp.R;
import com.filipvinkovic.weatherapp.rest.WeatherData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Filip on 30.11.2014..
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<WeatherData.DailyWeather> weatherData;
    private Context ctx;

    public RecyclerViewAdapter(List<WeatherData.DailyWeather> weatherData, Context ctx) {
        this.weatherData = weatherData;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Date currentDate = new Date(Long.parseLong(weatherData.get(position).unixDate)*1000);
        SimpleDateFormat format = new SimpleDateFormat("EEEE");
        final int iconId = ctx.getResources().getIdentifier("s" + weatherData.get(position).weather.get(0).icon, "drawable", ctx.getPackageName());

        viewHolder.dayName.setText(format.format(currentDate));
        viewHolder.weatherCondition.setText(weatherData.get(position).weather.get(0).main);
        viewHolder.minTemp.setText(weatherData.get(position).temp.min.intValue() + "°");
        viewHolder.maxTemp.setText(weatherData.get(position).temp.max.intValue() + "°");
        viewHolder.weatherIcon.setImageDrawable(ctx.getResources().getDrawable(iconId));
    }

    @Override
    public int getItemCount() {
        return weatherData.size();
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

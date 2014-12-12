package com.filipvinkovic.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Filip on 30.11.2014..
 */
public class WeatherData {

    @SerializedName("cod")
    String cod;
    @SerializedName("city")
    City city;
    @SerializedName("list")
    List<DailyWeather> dailyWeatherList;

    public class Temp {

        @SerializedName("day")
        Double day;
        @SerializedName("min")
        Double min;
        @SerializedName("max")
        Double max;
        @SerializedName("night")
        Double night;
    }

    public class Weather {

        @SerializedName("id")
        int id;
        @SerializedName("main")
        String main;
        @SerializedName("description")
        String description;
        @SerializedName("icon")
        String icon;
    }

    public class DailyWeather {

        @SerializedName("dt")
        String unixDate;
        @SerializedName("temp")
        Temp temp;
        @SerializedName("weather")
        List<Weather> weather;
    }

    public class City {

        @SerializedName("id")
        int id;
        @SerializedName("name")
        String name;
    }
}

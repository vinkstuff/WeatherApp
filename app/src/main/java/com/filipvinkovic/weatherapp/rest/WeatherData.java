package com.filipvinkovic.weatherapp.rest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Filip on 30.11.2014..
 */
public class WeatherData {

    @SerializedName("cod")
    public String cod;
    @SerializedName("city")
    public City city;
    @SerializedName("list")
    public List<DailyWeather> dailyWeatherList;

    public class Temp {

        @SerializedName("day")
        public Double day;
        @SerializedName("min")
        public Double min;
        @SerializedName("max")
        public Double max;
        @SerializedName("night")
        public Double night;
    }

    public class Weather {

        @SerializedName("id")
        public int id;
        @SerializedName("main")
        public String main;
        @SerializedName("description")
        public String description;
        @SerializedName("icon")
        public String icon;
    }

    public class DailyWeather {

        @SerializedName("dt")
        public String unixDate;
        @SerializedName("temp")
        public Temp temp;
        @SerializedName("weather")
        public List<Weather> weather;
    }

    public class City {

        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("coord")
        public Coord coord;
    }

    public class Coord {

        @SerializedName("lat")
        public Double lat;
        @SerializedName("lon")
        public Double lon;
    }
}

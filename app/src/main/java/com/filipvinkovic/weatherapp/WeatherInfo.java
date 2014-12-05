package com.filipvinkovic.weatherapp;

/**
 * Created by Filip on 30.11.2014..
 */
public class WeatherInfo {

    private String dayName,
                    weatherCondition;
    private int minTemp, maxTemp;

    public WeatherInfo(String dayName, String weatherCondition, int minTemp, int maxTemp) {
        this.dayName = dayName;
        this.weatherCondition = weatherCondition;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public String getDayName() {
        return dayName;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }
}

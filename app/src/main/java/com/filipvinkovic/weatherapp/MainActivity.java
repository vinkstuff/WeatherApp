package com.filipvinkovic.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<WeatherInfo> weatherInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherInfo wi1 = new WeatherInfo("Monday", "Rainy", 5, 10);
        WeatherInfo wi2 = new WeatherInfo("Tuesday", "Rainy", 5, 10);
        WeatherInfo wi3 = new WeatherInfo("Wednesday", "Rainy", 5, 10);
        WeatherInfo wi4 = new WeatherInfo("Thursday", "Rainy", 5, 10);
        WeatherInfo wi5 = new WeatherInfo("Friday", "Rainy", 5, 10);

        weatherInfoList = new ArrayList<WeatherInfo>();
        weatherInfoList.add(wi1);
        weatherInfoList.add(wi2);
        weatherInfoList.add(wi3);
        weatherInfoList.add(wi4);
        weatherInfoList.add(wi5);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(weatherInfoList);
        recyclerView.setAdapter(adapter);
    }
}

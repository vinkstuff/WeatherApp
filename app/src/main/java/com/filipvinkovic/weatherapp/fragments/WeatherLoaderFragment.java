package com.filipvinkovic.weatherapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;

import com.filipvinkovic.weatherapp.rest.WeatherAPI;
import com.filipvinkovic.weatherapp.rest.WeatherData;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Filip on 16.12.2014..
 */
public class WeatherLoaderFragment extends Fragment {
    ProgressListener progressListener;

    public interface ProgressListener {
        public void onCompletion(WeatherData weatherData);
    }

    public void setProgressListener(ProgressListener listener) {
        progressListener = listener;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            progressListener = (ProgressListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ProgressListener");
        }
    }

    public void startLoading() {
        WeatherAPI.getApiService().getWeatherInfo("45.811572", "15.952148", "metric", "6",
                new Callback<WeatherData>() {
                    @Override
                    public void success(WeatherData weatherData, Response response) {
                        progressListener.onCompletion(weatherData);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("WeatherApp", error.getMessage());
                    }
                });
    }
}

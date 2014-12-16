package com.filipvinkovic.weatherapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.filipvinkovic.weatherapp.R;

/**
 * Created by Filip on 16.12.2014..
 */
public class SplashFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.splash_layout, container, false);
    }
}

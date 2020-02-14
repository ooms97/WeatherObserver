package com.example.weatherobserver;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        WeatherData weatherData = new WeatherData(getApplicationContext());
        WeekDisplay weekDisplay = new WeekDisplay(this, weatherData);
        weatherData.getMeasurements();


    }

}

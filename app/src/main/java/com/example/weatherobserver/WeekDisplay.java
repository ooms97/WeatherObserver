package com.example.weatherobserver;

import android.app.Activity;
import android.widget.TextView;

public class WeekDisplay implements Observer, DisplayData {
    private Subject weatherData;
    private ConsolidatedWeather[] consolidatedWeathers;
    Activity mActivity;

    public WeekDisplay(Activity activity, Subject weatherData) {
        mActivity = activity;
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        TextView textView = mActivity.findViewById(R.id.tv);
        StringBuilder sb = new StringBuilder();
        for(ConsolidatedWeather consolidatedWeather : consolidatedWeathers){
            sb.append("Temperature: " + consolidatedWeather.the_temp.substring(0, 2) + "\n");
        }

        textView.setText(sb.toString());
    }

    @Override
    public void update(ConsolidatedWeather[] consolidatedWeather) {
        consolidatedWeathers = consolidatedWeather;
        display();
    }
}

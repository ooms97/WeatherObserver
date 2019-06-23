package com.example.weatherobserver;

import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;

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
        ListView listView = (ListView) mActivity.findViewById(R.id.list_item);
        ArrayList<SingleWeatherItem> items = new ArrayList<>();

        for(ConsolidatedWeather consolidatedWeather : consolidatedWeathers){
            SingleWeatherItem singleWeatherItem = new SingleWeatherItem();
            singleWeatherItem.setMax_temp(consolidatedWeather.max_temp.substring(0,4));
            singleWeatherItem.setMin_temp(consolidatedWeather.min_temp.substring(0,4));
            singleWeatherItem.setWeatherStatus(consolidatedWeather.weather_state_name);

            items.add(singleWeatherItem);
        }

        WeatherListAdapter weatherListAdapter = new WeatherListAdapter(mActivity, items);
        listView.setAdapter(weatherListAdapter);
        weatherListAdapter.notifyDataSetChanged();
    }

    @Override
    public void update(ConsolidatedWeather[] consolidatedWeather) {
        consolidatedWeathers = consolidatedWeather;
        display();
    }
}

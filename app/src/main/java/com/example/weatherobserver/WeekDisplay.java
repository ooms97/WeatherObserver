package com.example.weatherobserver;

import android.app.Activity;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        SimpleDateFormat output = new SimpleDateFormat("EEE dd MMM");

        for(ConsolidatedWeather consolidatedWeather : consolidatedWeathers){
            SingleWeatherItem singleWeatherItem = new SingleWeatherItem();
            String date_output ="";
            try {
                date = input.parse(consolidatedWeather.applicable_date);
                date_output = output.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            singleWeatherItem.setApplicableDate(date_output.replaceAll("\\.", ""));
            singleWeatherItem.setHumidity(consolidatedWeather.humidity);
            singleWeatherItem.setPressure(consolidatedWeather.air_pressure.substring(0,4));
            singleWeatherItem.setVisibility(consolidatedWeather.visibility.substring(0,4));
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

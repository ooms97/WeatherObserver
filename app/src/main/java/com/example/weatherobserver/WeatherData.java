package com.example.weatherobserver;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;


public class WeatherData implements Subject {
    private final String TAG = WeatherData.class.getSimpleName();
    private static final String URL = "https://www.metaweather.com/api/location/4118/";
    private ArrayList<Observer> observers;
    private ConsolidatedWeather[] consolidatedWeather;
    private Context mContext;

    public WeatherData(Context context) {
        observers = new ArrayList<>();
        mContext = context;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);

    }

    @Override
    public void notifyObservers() {
        for(Observer o: observers){
            o.update(consolidatedWeather);
        }
    }

    public void measurementsChanged(){
        notifyObservers();
    }

    public void getMeasurements(){
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        JSONArray jsonArray = response.optJSONArray("consolidated_weather");
                        if(jsonArray != null){
                            consolidatedWeather = gson.fromJson(jsonArray.toString(), ConsolidatedWeather[].class);
                            measurementsChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Volley Error:" + error.getMessage());
                    }
                }) {
        };
        // Add the request to the RequestQueue.
        requestQueue.add(jsonObjectRequest);
    }


}

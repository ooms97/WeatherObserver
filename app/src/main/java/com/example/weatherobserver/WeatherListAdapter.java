package com.example.weatherobserver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class WeatherListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<SingleWeatherItem> mSingleWeatherItems;

    public WeatherListAdapter(Context context, ArrayList<SingleWeatherItem> arrayList) {
        mContext = context;
        mSingleWeatherItems = arrayList;
    }

    @Override
    public int getCount() {
        return mSingleWeatherItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mSingleWeatherItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.single_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SingleWeatherItem currentItem = (SingleWeatherItem) getItem(position);
        viewHolder.weatherIcon.setImageResource(getImageResource(currentItem.getWeatherStatus()));
        viewHolder.minTemp.setText("Min Temp: " + currentItem.getMin_temp());
        viewHolder.maxTemp.setText("Max Temp: " + currentItem.getMax_temp());

        return convertView;
    }

    private class ViewHolder {
        ImageView weatherIcon;
        TextView minTemp;
        TextView maxTemp;

        public ViewHolder(View view) {
            weatherIcon = (ImageView) view.findViewById(R.id.weatherStatusIcon);
            minTemp = (TextView) view.findViewById(R.id.minTemp);
            maxTemp = (TextView) view.findViewById(R.id.maxTemp);
        }
    }

    public int getImageResource(String s){
        int icon;

        switch (s){
            case "Snow":
                icon = R.drawable.sn;
                break;
            case "Sleet":
                icon = R.drawable.sl;
                break;
            case "Hail":
                icon = R.drawable.h;
                break;
            case "Thunderstorm":
                icon = R.drawable.t;
                break;
            case "Heavy Rain":
                icon = R.drawable.hr;
                break;
            case "Light Rain":
                icon = R.drawable.lr;
                break;
            case "Showers":
                icon = R.drawable.s;
                break;
            case "Heavy Cloud":
                icon = R.drawable.hc;
                break;
            case "Light Cloud":
                icon = R.drawable.lc;
                break;
            case "Clear":
                icon = R.drawable.c;
                break;
            default:
                icon = R.drawable.c;
                break;
        }
        return icon;
    }

}

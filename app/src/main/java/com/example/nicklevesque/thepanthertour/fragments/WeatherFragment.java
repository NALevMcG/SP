package com.example.nicklevesque.thepanthertour.fragments;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nicklevesque.thepanthertour.R;
import com.example.nicklevesque.thepanthertour.WeatherJSON;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ntlevesque on 3/10/17.
 *
 */

public class WeatherFragment extends Fragment {


    Typeface wF;
    RelativeLayout layout;

    //TextView variables to store and display our information
    TextView cF;
    TextView uF;
    TextView dF;
    TextView cTF;
    TextView wI;

    Handler handler;

    //Constructor
    public WeatherFragment(){
        handler = new Handler();
    }

    /* Called to do initial creation of the fragment. */
    public void onCreate(Bundle savedInstanceState) {
        getActivity().setTitle(R.string.weather_title);
        super.onCreate(savedInstanceState);
        //custom font I imported into my project, used to create the weather symbols
        wF = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weather.ttf");
        updateWeatherData("Plymouth NH");
    }

    //creates and returns the view hierarchy associated with the fragment. returns the rootView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.weather_frag, container, false);
        //Initiate all of my textviews to populate them with the data we receive from the JSON object
        cF = (TextView)rootView.findViewById(R.id.city_field);
        uF = (TextView)rootView.findViewById(R.id.updated_field);
        dF = (TextView)rootView.findViewById(R.id.details_field);
        cTF = (TextView)rootView.findViewById(R.id.current_temperature_field);
        wI = (TextView) rootView.findViewById(R.id.weather_icon);

        layout = (RelativeLayout) rootView.findViewById(R.id.weatherbackground);

        wI.setTypeface(wF);
        return rootView;
    }



    /* method used to continuously update weather for specific city (Plymouth) when it is available*/
    private void updateWeatherData(final String city){
        new Thread(){
            public void run(){
                final JSONObject json = WeatherJSON.getJSON(getActivity(), city);
                if(json != null){
                    handler.post(new Runnable(){
                        public void run(){
                            renderWeather(json);
                        }
                    });
                } else {

                }
            }
        }.start();
    }

    /*
    Method used to render the data we obtained from the WeatherJSON Class, We use a try catch statement
    here to ensure that we arent missing any data provided from Openweathermap.org, if we are, thrown an error message
    if not, assign each textview with the corresponding data
      */
    private void renderWeather(JSONObject json){
        try {
            cF.setText(json.getString("name").toUpperCase(Locale.US) +
                    ", " +
                    json.getJSONObject("sys").getString("country"));


            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            Double fTemp = main.getDouble("temp");
            fTemp = fTemp * 1.8 + 32;
            dF.setText(
                    details.getString("description").toUpperCase(Locale.US) +
                            "\n" + "Humidity: " + main.getString("humidity") + "%" +
                            "\n" + "Pressure: " + main.getString("pressure") + " hPa");

            cTF.setText(
                    String.format("%.2f", fTemp)+ "°F");

            DateFormat df = DateFormat.getDateTimeInstance();
            String updatedOn = df.format(new Date(json.getLong("dt")*1000));
            uF.setText("Last update: " + updatedOn);

            setWeatherIcon(details.getInt("id"),
                    json.getJSONObject("sys").getLong("sunrise") * 1000,
                    json.getJSONObject("sys").getLong("sunset") * 1000);

        }catch(Exception e){
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
    }

    /* sets weather icon based on the id given back by the JSON data we grabbed from OpenWeatherMap */
    private void setWeatherIcon(int actualId, long sunrise, long sunset){

        int id = actualId / 100;
        String icon = "";
        if(actualId == 800){
            long currentTime = new Date().getTime();
            if(currentTime>=sunrise && currentTime<sunset) {
                icon = getActivity().getString(R.string.weather_sunny);
                layout.setBackgroundResource(R.drawable.day);
            } else {
                icon = getActivity().getString(R.string.weather_clear_night);
                layout.setBackgroundResource(R.drawable.night);
            }
        } else {
            switch(id) {
                case 2 : icon = getActivity().getString(R.string.weather_thunder);
                    break;
                case 3 : icon = getActivity().getString(R.string.weather_drizzle);
                    layout.setBackgroundResource(R.drawable.rain);
                    break;
                case 7 : icon = getActivity().getString(R.string.weather_foggy);
                    break;
                case 8 : icon = getActivity().getString(R.string.weather_cloudy);
                    break;
                case 6 : icon = getActivity().getString(R.string.weather_snowy);
                    break;
                case 5 : icon = getActivity().getString(R.string.weather_rainy);
                    break;
            }
        }
        wI.setText(icon);
    }


}


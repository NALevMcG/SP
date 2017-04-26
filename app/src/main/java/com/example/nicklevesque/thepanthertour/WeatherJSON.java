package com.example.nicklevesque.thepanthertour;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

/**
 * Created by nicklevesque on 3/11/17
 *Class used to fetch JSON data from OpenWeatherMap web services
 */
public class WeatherJSON {

    private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";

    public static JSONObject getJSON(Context context, String city){
        try {
            //Connect to link above, this is where we will pull our JSON data
            URL url = new URL(String.format(OPEN_WEATHER_MAP_API, city));
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            //My openweathermap api key
            connection.addRequestProperty("x-api-key", context.getString(R.string.open_weather_maps_app_id));

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            //read in JSON data and write it to the StringBuffer
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
                reader.close();

            //Create a new JSONObject with the data on it
            JSONObject data = new JSONObject(json.toString());

            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){
                return null;
            }

            return data;

        }
        catch(Exception e){
            return null;
        }
    }
}

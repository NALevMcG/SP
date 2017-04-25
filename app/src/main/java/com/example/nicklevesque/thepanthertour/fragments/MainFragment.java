package com.example.nicklevesque.thepanthertour.fragments;


import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nicklevesque.thepanthertour.R;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Button;





/**
 * Created by nicklevesque on 4/7/17.
 */
public class MainFragment extends Fragment {


    //creates and returns the view hierarchy associated with the fragment. returns the rootView
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //Create variables for your button
        final Button mapButton = (Button) rootView.findViewById(R.id.mapButton);
        final Button weatherButton = (Button) rootView.findViewById(R.id.weatherButton);
        final Button pofButton = (Button) rootView.findViewById(R.id.pofButton);
        final Button slideButton = (Button) rootView.findViewById(R.id.cButton);


        //set onClickListeners to launch different fragments when the corresponding button is pressed

        //mapButton onClickListener
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                MapsFragment mFrag = new MapsFragment();
                getActivity().getFragmentManager().beginTransaction().replace(R.id.main_drawer, mFrag).addToBackStack("my_fragment").commit();
            }
        });

        //weatherButton onClickListener
        weatherButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                WeatherFragment wFragment = new WeatherFragment();
                getFragmentManager().beginTransaction().replace(R.id.main_drawer, wFragment).addToBackStack("my_fragment").commit();
            }
        });

        //Points of interest onClickListener
        pofButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ListFragment lFragment = new ListFragment();
                getFragmentManager().beginTransaction().replace(R.id.main_drawer, lFragment).addToBackStack("my_fragment").commit();
            }
        });

        //slideshow onClickListener
        slideButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SlideShowFragment sFragment = new SlideShowFragment();
                getFragmentManager().beginTransaction().replace(R.id.main_drawer, sFragment).addToBackStack("my_fragment").commit();
            }
        });


        return rootView;

    }



}

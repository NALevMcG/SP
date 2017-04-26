package com.example.nicklevesque.thepanthertour.fragments;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewSwitcher;

import com.example.nicklevesque.thepanthertour.R;

/**
 * Created by nicklevesque on 4/3/17.
 * Fragment associaeted with the list part of my places of interest functionality of my app
 * Transmits coordinates to DirectionsFragment
 */
public class ListFragment extends Fragment {

    /* Called to do initial creation of the fragment. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.main_list, container, false);
        final ViewSwitcher vw = (ViewSwitcher) rootView.findViewById(R.id.simpleViewSwitcher);

        //buttons for my Points of interest list
        final Button mountainButton = (Button) rootView.findViewById(R.id.mountainButton);
        final Button campingButton = (Button) rootView.findViewById(R.id.campingButton);
        final Button bridgeButton = (Button) rootView.findViewById(R.id.bridgeButton);

        mountainButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                vw.setDisplayedChild(1);
            }
        });

        //Buttons for my trail/hiking list
        final Button pm = (Button) rootView.findViewById(R.id.plymouthMountain);
        final Button rf = (Button) rootView.findViewById(R.id.rainbowFalls);
        final Button rs = (Button) rootView.findViewById(R.id.rattleSnake);
        final Button ww = (Button) rootView.findViewById(R.id.whittenWoods);
        final Button fg = (Button) rootView.findViewById(R.id.flumeGorge);
        final Button ph = (Button) rootView.findViewById(R.id.peakedHill);
        final Button wad = (Button) rootView.findViewById(R.id.welchandDicky);
        final Button mc = (Button) rootView.findViewById(R.id.mountCiley);
        final Button pc = (Button) rootView.findViewById(R.id.polarCaves);
        final Button cf = (Button) rootView.findViewById(R.id.champney);
        final Button lrg = (Button) rootView.findViewById(R.id.lostRiver);



        /*
        Onclick listener for every button, each button transmits unique coordinates to directionsfragment
         */
        pm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of plymouth mountain to DirectionsFragment
                args.putDouble("latitude", 43.700192);
                args.putDouble("longitude", -71.708699);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        rf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of rainbow falls mountain to DirectionsFragment
                args.putDouble("latitude", 43.729550);
                args.putDouble("longitude", -71.689956);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        rs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of rattlesnake trail to DirectionsFragment
                args.putDouble("latitude", 43.776629 );
                args.putDouble("longitude", -71.542871);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        ww.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of Whittenwoods trail to DirectionsFragment
                args.putDouble("latitude", 43.708102);
                args.putDouble("longitude",-71.633722);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        fg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of the flume gorge to DirectionsFragment
                args.putDouble("latitude", 44.096210);
                args.putDouble("longitude",-71.681962);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        ph.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of peaked hill traill to DirectionsFragment
                args.putDouble("latitude", 43.899914);
                args.putDouble("longitude", -71.689744);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        wad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of welch and dickey trail to DirectionsFragment
                args.putDouble("latitude", 43.904200);
                args.putDouble("longitude", -71.588814);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        mc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of mount ciley to DirectionsFragment
                args.putDouble("latitude", 43.986273);
                args.putDouble("longitude", -71.682907);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        pc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of polar caves to DirectionsFragment
                args.putDouble("latitude", 43.782483);
                args.putDouble("longitude", -71.780827);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        cf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of champney to DirectionsFragment
                args.putDouble("latitude", 43.990271);
                args.putDouble("longitude", -71.299171);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        lrg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of the lost river to DirectionsFragment
                args.putDouble("latitude", 44.0272012);
                args.putDouble("longitude", -71.8064888);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });




        return rootView;


    }


}
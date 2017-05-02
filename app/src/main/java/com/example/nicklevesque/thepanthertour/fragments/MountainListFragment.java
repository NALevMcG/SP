package com.example.nicklevesque.thepanthertour.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nicklevesque.thepanthertour.R;


public class MountainListFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_mountain_list, container, false);

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
        final Button ht = (Button) rootView.findViewById(R.id.hubbardbrook);
        final Button mm = (Button) rootView.findViewById(R.id.mountmajor);
        final Button mi = (Button) rootView.findViewById(R.id.mount_isreal);




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
                args.putString("name", "Plymouth Mountain");
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
                args.putString("name", "Rainbow Falls");

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
                args.putString("name", "Rattlesnake trail");

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
                args.putString("name", "Whitten Woods trail");

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
                args.putString("name", "The Flume Gorge");

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
                args.putString("name", "Peaked Hill Trail");
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
                args.putString("name", "Welch and Dickey");
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
                args.putString("name", "Mount Ciley");

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
                args.putString("name", "The Polar Caves");

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
                args.putString("name", "Champney trail");
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
                args.putString("name", "The Lost River");
                args.putDouble("latitude", 44.0272012);
                args.putDouble("longitude", -71.8064888);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        ht.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of the lost river to DirectionsFragment
                args.putString("name", "Hubbard Trail");
                args.putDouble("latitude", 43.946319);
                args.putDouble("longitude", -71.789088);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        mm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of the lost river to DirectionsFragment
                args.putString("name", "Mount Major");
                args.putDouble("latitude", 43.509879);
                args.putDouble("longitude",  -71.272889);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        mi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of the lost river to DirectionsFragment
                args.putString("name", "Mount Isreal Trail");
                args.putDouble("latitude", 43.828551);
                args.putDouble("longitude",  -71.484716);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });




        return rootView;

    }


}

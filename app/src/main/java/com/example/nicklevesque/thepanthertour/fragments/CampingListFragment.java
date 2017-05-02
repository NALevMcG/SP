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


public class CampingListFragment extends Fragment {


    public CampingListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_camping_list, container, false);

        //Buttons for my trail/hiking list
        final Button wvc = (Button) rootView.findViewById(R.id.watervillecampground);
        final Button prc = (Button) rootView.findViewById(R.id.pemirivercampground);
        final Button twc = (Button) rootView.findViewById(R.id.tamworthcampground);
        final Button bbc = (Button) rootView.findViewById(R.id.branchbrookcampground);
        final Button cc = (Button) rootView.findViewById(R.id.camptoncampground);
        final Button trc = (Button) rootView.findViewById(R.id.tripolirdcampground);
        final Button ab = (Button) rootView.findViewById(R.id.amesbrook);
        final Button rp = (Button) rootView.findViewById(R.id.russelpond);
        final Button wc = (Button) rootView.findViewById(R.id.waterestcampground);
        final Button mpc = (Button) rootView.findViewById(R.id.mountainpine);
        final Button lrg = (Button) rootView.findViewById(R.id.lostRiver);
        final Button ht = (Button) rootView.findViewById(R.id.hubbardbrook);
        final Button mm = (Button) rootView.findViewById(R.id.mountmajor);
        final Button mi = (Button) rootView.findViewById(R.id.mount_isreal);


        /*
        Onclick listener for every button, each button transmits unique coordinates to directionsfragment
         */
        wvc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of plymouth mountain to DirectionsFragment
                args.putDouble("latitude", 43.944523);
                args.putDouble("longitude", -71.510347);
                args.putString("name", "Waterville Valley Campground");
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        prc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of rainbow falls mountain to DirectionsFragment
                args.putDouble("latitude", 43.871497);
                args.putDouble("longitude", -71.664601);
                args.putString("name", "Pemi River Campground");

                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        twc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of rattlesnake trail to DirectionsFragment
                args.putDouble("latitude", 43.847259 );
                args.putDouble("longitude", -71.257359);
                args.putString("name", "Tamworth Campground");

                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        bbc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of Whittenwoods trail to DirectionsFragment
                args.putDouble("latitude", 43.853700);
                args.putDouble("longitude",-71.657775);
                args.putString("name", "Branch Brook Campground");

                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        cc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of the flume gorge to DirectionsFragment
                args.putDouble("latitude", 43.873060);
                args.putDouble("longitude",-71.624485);
                args.putString("name", "Campton Campground");

                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        trc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of peaked hill traill to DirectionsFragment
                args.putDouble("latitude", 43.995173);
                args.putDouble("longitude",  -71.636371);
                args.putString("name", "Tripoli Road Campground");
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        ab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of welch and dickey trail to DirectionsFragment
                args.putDouble("latitude", 43.688293);
                args.putDouble("longitude", -71.622805);
                args.putString("name", "Ames Brook Campground");
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        rp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of mount ciley to DirectionsFragment
                args.putDouble("latitude", 44.008520);
                args.putDouble("longitude", -71.648945);
                args.putString("name", "Russel Pond Campground");

                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        wc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of polar caves to DirectionsFragment
                args.putDouble("latitude", 43.971110);
                args.putDouble("longitude", -71.677082);
                args.putString("name", "Waterest Campground");

                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        mpc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of champney to DirectionsFragment
                args.putDouble("latitude", 43.990271);
                args.putDouble("longitude", -71.299171);
                args.putString("name", "Mountain Pines Campground");
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

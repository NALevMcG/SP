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


public class SkiListFragment extends Fragment {

    public SkiListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_ski_list, container, false);

        //Buttons for my trail/hiking list
        final Button ws = (Button) rootView.findViewById(R.id.watervilleski);
        final Button ls = (Button) rootView.findViewById(R.id.loonski);
        final Button cs = (Button) rootView.findViewById(R.id.camptonski);
        final Button gs = (Button) rootView.findViewById(R.id.gunstockski);
        final Button ds = (Button) rootView.findViewById(R.id.dartmouthski);
        final Button rms = (Button) rootView.findViewById(R.id.raggedski);
        final Button wbs = (Button) rootView.findViewById(R.id.whalebackski);
        final Button kps = (Button) rootView.findViewById(R.id.kingski);
        final Button wbas = (Button) rootView.findViewById(R.id.wolfski);
        final Button bs = (Button) rootView.findViewById(R.id.brettonski);
        final Button ss = (Button) rootView.findViewById(R.id.sunapeeski);
        final Button csa = (Button) rootView.findViewById(R.id.cranmoreski);
        final Button as = (Button) rootView.findViewById(R.id.attitashski);
        final Button cannonski = (Button) rootView.findViewById(R.id.cannonski);
        final Button pps = (Button) rootView.findViewById(R.id.patspeakski);
        final Button srs = (Button) rootView.findViewById(R.id.sundayriverski);



        /*
        Onclick listener for every button, each button transmits unique coordinates to directionsfragment
         */
        ws.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of plymouth mountain to DirectionsFragment
                args.putDouble("latitude", 43.964930);
                args.putDouble("longitude", -71.525094);
                args.putString("name", "Waterville Ski Area");
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        ls.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of rainbow falls mountain to DirectionsFragment
                args.putDouble("latitude", 44.056435);
                args.putDouble("longitude", -71.633227);
                args.putString("name", "Loon Ski Area");

                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        cs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of rattlesnake trail to DirectionsFragment
                args.putDouble("latitude", 43.867174);
                args.putDouble("longitude",  -71.610608 );
                args.putString("name", "Campton Mountain Ski Area");

                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        gs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of Whittenwoods trail to DirectionsFragment
                args.putDouble("latitude", 43.545780);
                args.putDouble("longitude", -71.366744);
                args.putString("name", "Gunstock Ski Area");

                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        ds.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of the flume gorge to DirectionsFragment
                args.putDouble("latitude", 43.873060);
                args.putDouble("longitude",-71.624485);
                args.putString("name", "Dartmouth Skiway");

                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        rms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of peaked hill traill to DirectionsFragment
                args.putDouble("latitude", 43.503207);
                args.putDouble("longitude",-71.8427591);
                args.putString("name", "Ragged Ski Area");
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        wbs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of welch and dickey trail to DirectionsFragment
                args.putDouble("latitude", 43.601934);
                args.putDouble("longitude", -72.180471);
                args.putString("name", "Whaleback Mountain Ski Area");
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        kps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of mount ciley to DirectionsFragment
                args.putDouble("latitude", 43.8680577);
                args.putDouble("longitude", -71.1589416);
                args.putString("name", "King Pine Ski Area");

                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        wbas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of polar caves to DirectionsFragment
                args.putDouble("latitude", 43.6115266);
                args.putDouble("longitude", -71.2991468);
                args.putString("name", "Wolfeboro Abenaki Ski Area");

                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        bs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of champney to DirectionsFragment
                args.putDouble("latitude", 44.2592406);
                args.putDouble("longitude", -71.5302778);
                args.putString("name", "Bretton Woods Ski Area");
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        ss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of the lost river to DirectionsFragment
                args.putString("name", "Mount Sunapee Ski Area");
                args.putDouble("latitude", 43.3302741);
                args.putDouble("longitude", -72.1024714);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        csa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of the lost river to DirectionsFragment
                args.putString("name", "Cranmore Ski Area");
                args.putDouble("latitude", 44.0568309);
                args.putDouble("longitude",-71.1696324);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        as.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of the lost river to DirectionsFragment
                args.putString("name", "Attitash Ski Area");
                args.putDouble("latitude", 44.0827603);
                args.putDouble("longitude",-71.2994384);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        cannonski.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of the lost river to DirectionsFragment
                args.putString("name", "Cannon Ski Area");
                args.putDouble("latitude", 44.177299);
                args.putDouble("longitude", -71.702297);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        pps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of the lost river to DirectionsFragment
                args.putString("name", "Pats Peak Ski Area");
                args.putDouble("latitude", 43.162246);
                args.putDouble("longitude", -71.799915);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });

        srs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DirectionsFragment ldf = new DirectionsFragment ();
                Bundle args = new Bundle();
                //Pass Long and Lat of the lost river to DirectionsFragment
                args.putString("name", "Sunday River Ski Area");
                args.putDouble("latitude", 44.467233);
                args.putDouble("longitude", -70.846765);
                ldf.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.main_drawer, ldf).addToBackStack("my_fragment").commit();

            }
        });




        return rootView;

    }



}

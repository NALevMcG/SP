package com.example.nicklevesque.thepanthertour.fragments;

import android.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Cool Spots");
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.main_list, container, false);
        //final ViewSwitcher vw = (ViewSwitcher) rootView.findViewById(R.id.simpleViewSwitcher);

        //buttons for my Points of interest list
        final Button mountainButton = (Button) rootView.findViewById(R.id.mountainButton);
        final Button campingButton = (Button) rootView.findViewById(R.id.campingButton);
        final Button skiButton = (Button) rootView.findViewById(R.id.skiButton);


        mountainButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                MountainListFragment mountainFrag = new MountainListFragment();
                getActivity().getFragmentManager().beginTransaction().replace(R.id.main_drawer, mountainFrag).addToBackStack("my_fragment").commit();

            }
        });

        campingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                CampingListFragment clg = new CampingListFragment();
                getActivity().getFragmentManager().beginTransaction().replace(R.id.main_drawer, clg).addToBackStack("my_fragment").commit();
            }
        });

        skiButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                SkiListFragment skiFrag = new SkiListFragment();
                getActivity().getFragmentManager().beginTransaction().replace(R.id.main_drawer, skiFrag).addToBackStack("my_fragment").commit();

            }
        });

       return rootView;


    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item= menu.findItem(R.id.action_settings);
        item.setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }


}
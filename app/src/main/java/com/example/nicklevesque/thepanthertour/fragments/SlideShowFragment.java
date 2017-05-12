package com.example.nicklevesque.thepanthertour.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nicklevesque.thepanthertour.R;
import com.example.nicklevesque.thepanthertour.SlideShowAdapter;


public class SlideShowFragment extends Fragment {

    //initialize variables
    ViewPager viewpager;
    SlideShowAdapter adapter;

    /* Called to do initial creation of the fragment. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    //creates and returns the view hierarchy associated with the fragment. returns the rootView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_slide_show, container, false);



        adapter = new SlideShowAdapter(getContext());

        viewpager = (ViewPager)rootView.findViewById(R.id.viewpager1);
        viewpager.setAdapter( adapter );

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Campus Photos");

        return rootView;

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item= menu.findItem(R.id.action_settings);
        item.setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }


}

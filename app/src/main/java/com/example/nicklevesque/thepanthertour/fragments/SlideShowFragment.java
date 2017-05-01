package com.example.nicklevesque.thepanthertour.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nicklevesque.thepanthertour.Names;
import com.example.nicklevesque.thepanthertour.R;
import com.example.nicklevesque.thepanthertour.SlideShowAdapter;

import java.util.ArrayList;
import java.util.List;


public class SlideShowFragment extends Fragment {

    //initialize variables
    ViewPager viewpager;
    SlideShowAdapter adapter;
    List<Names> items = new ArrayList<>();

    /* Called to do initial creation of the fragment. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items.add(new Names("First Top","First Bot"));
        items.add(new Names("Second Top","Second Bot"));
        items.add(new Names("Third Top","Third Bot"));

    }

    //creates and returns the view hierarchy associated with the fragment. returns the rootView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_slide_show, container, false);


        adapter = new SlideShowAdapter(getContext(), items);

        viewpager = (ViewPager)rootView.findViewById(R.id.viewPager1);
        viewpager.setAdapter( adapter );

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Campus Photos");

        return rootView;

    }


}

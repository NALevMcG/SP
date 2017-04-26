package com.example.nicklevesque.thepanthertour.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nicklevesque.thepanthertour.Names;
import com.example.nicklevesque.thepanthertour.R;
import com.example.nicklevesque.thepanthertour.SlideShowAdapter;

import java.util.ArrayList;
import java.util.List;


public class SlideShowFragment extends Fragment {

    ViewPager viewpager;
    SlideShowAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //creates and returns the view hierarchy associated with the fragment. returns the rootView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_slide_show, container, false);

        List<Names> items = new ArrayList<Names>();
        items.add(new Names("First Top","First Bot"));
        items.add(new Names("Second Top","Second Bot"));
        items.add(new Names("Third Top","Third Bot"));

        adapter = new SlideShowAdapter(getContext(), items);

        viewpager = (ViewPager)rootView.findViewById(R.id.viewPager1);
        viewpager.setAdapter( adapter );

        return rootView;

    }


}

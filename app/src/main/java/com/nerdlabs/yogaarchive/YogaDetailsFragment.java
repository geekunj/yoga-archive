package com.nerdlabs.yogaarchive;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;


public class YogaDetailsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        View view = inflater.inflate(R.layout.fragment_yoga_details, container, false);

        TextView textdes = (TextView) view.findViewById(R.id.tv_pose_des);
        ViewCompat.setElevation(textdes, 5);

        return view;
    }
}

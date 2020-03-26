package com.swankapps.corona.preventcorona.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swankapps.corona.preventcorona.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrecautionFragment extends Fragment {

    public PrecautionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_precaution, container, false);
    }
}

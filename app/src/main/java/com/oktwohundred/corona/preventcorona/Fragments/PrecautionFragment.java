package com.oktwohundred.corona.preventcorona.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oktwohundred.corona.preventcorona.Model.feeds;
import com.oktwohundred.corona.preventcorona.R;
import com.oktwohundred.corona.preventcorona.Adapter.feedsAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrecautionFragment extends Fragment {

    RecyclerView precCycler;
    feedsAdapter fadapter;
    List<feeds> feedItems;

    public PrecautionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View pv = inflater.inflate(R.layout.fragment_precaution, container, false);
        precCycler = pv.findViewById(R.id.precCycler);
        precCycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        feedItems = new ArrayList<>();
        fadapter = new feedsAdapter(getContext(), feedItems);
        loadAllPrecautions();

        return pv;
    }

    private void loadAllPrecautions() {
        feedItems.clear();
        feedItems.add(new feeds("precaution", "Stay home if you can.", "", "You can do your part to help your community and the world. Do not get close to other people.\n" +
                "\n" + "This is called “social distancing” or “physical distancing,” and is basically a call to stand far away from other people. Experts believe the coronavirus travels through droplets, so limiting your exposure to other people is a good way to protect yourself.", 3.5f, false));
        feedItems.add(new feeds("precaution","Wash your hands. With soap. Then wash them again.","","A refresher: Wet your hands and scrub them with soap, taking care to get between your fingers and under your nails. Wash for at least 20 seconds (or about the time it takes to sing “Happy Birthday” twice), and dry. Make sure you get your thumbs, too. The C.D.C. also recommends you avoid touching your eyes, nose and mouth with unwashed hands (tough one, we know).", 4.4f, false));
        feedItems.add(new feeds("precaution","Don’t stockpile masks.","","Face masks have become a symbol of coronavirus, but stockpiling them might do more harm than good.\n" +
                "\n" +
                "First, they don’t do much to protect you. Most surgical masks are too loose to prevent inhalation of the virus.", 5.0f, false));
        feedItems.add(new feeds("precaution","If surfaces are dirty, clean them","","use detergent or soap and water prior to disinfection. Full information on how to disinfect found here.", 3.5f, false));
        precCycler.setAdapter(fadapter);
        fadapter.notifyDataSetChanged();
    }
}

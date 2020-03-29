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
public class RemedyFragment extends Fragment {

    List<feeds> feedItems;
    feedsAdapter fadapter;
    RecyclerView remCycler;
    public RemedyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rv = inflater.inflate(R.layout.fragment_remedy, container, false);
        remCycler = rv.findViewById(R.id.remCycler);
        remCycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        feedItems = new ArrayList<>();
        fadapter = new feedsAdapter(getContext(), feedItems);
        loadAllRemedies();
        return rv;
    }
    private void loadAllRemedies() {
        feedItems.clear();
        feedItems.add(new feeds("remedy", "Ginger Tea", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", 3.5f, false));
        feedItems.add(new feeds("remedy", "Hot Tea", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", 5.0f, false));
        feedItems.add(new feeds("remedy", "Herbal Tea", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", 2.5f, false));
        feedItems.add(new feeds("remedy", "Good Tea", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", 2f, false));
        feedItems.add(new feeds("remedy", "Cure is  Tea", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", 4f, false));
        remCycler.setAdapter(fadapter);
        fadapter.notifyDataSetChanged();
    }
}
